package com.okt.controller;

import com.okt.entity.*;
import com.okt.service.AddressService;
import com.okt.service.CostService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/18
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/cost")
public class CostController {
    @Autowired
    CostService costService;
    @Autowired
    AddressService addressService;

    /**
     * 前往生活水电费用
     *
     * @return
     */
    @RequestMapping("/toMaintenance")
    public String toMaintenance(HttpServletRequest request, ModelMap map) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Maintenance> oneMaintenance = costService.findOneMaintenance(user.getR_username());
        for (Maintenance maintenance : oneMaintenance) {
            maintenance.setDate_str(DateFormat.dateToString(maintenance.getDate(), "yyyy-MM"));
            String addressName = addressService.findAddressNameById(maintenance.getH_id());
            maintenance.setH_name(addressName);
        }
        map.addAttribute("list", oneMaintenance);
        return "user/maintenance";
    }

    /**
     * 物业管理费按月查询
     */
    @RequestMapping("/findMaintenanceByMonth")
    public String toFindMaintenanceByMonth(String month, ModelMap map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Maintenance> maintenances = costService.findMainByMonth(month, user.getR_username());
        for (Maintenance maintenance : maintenances) {
            maintenance.setDate_str(DateFormat.dateToString(maintenance.getDate(), "yyyy-MM"));
            String addressName = addressService.findAddressNameById(maintenance.getH_id());
            maintenance.setH_name(addressName);
        }
        map.addAttribute("list", maintenances);
        return "user/maintenance";
    }

    /**
     * 前往停车费用
     *
     * @return
     */
    @RequestMapping("/toParkFee")
    public String toParkFee(HttpServletRequest request, ModelMap map) {
        HttpSession session = request.getSession();
        Date date = new Date();
        User user = (User) session.getAttribute("user");
        List<ParkFee> parkFeeList = costService.findOneParkFee(user.getR_username());
        for (ParkFee parkFee : parkFeeList) {
            int days = (int) ((parkFee.getExpire().getTime() - date.getTime()) / (24 * 3600 * 1000)) + 1;
            if (days < 0) {
                days = 0;
                costService.setParkfeeArrearage(parkFee.getCar_id());
            }
            parkFee.setExpire_str(DateFormat.dateToString(parkFee.getExpire(), "yyyy-MM-dd"));
            parkFee.setBalance(days);
            parkFee.setPlaceStr(parkFee.getPlace()==0?"室内":"室外");
        }
        map.addAttribute("list", parkFeeList);
        return "user/parkfee";
    }

    /**
     * 物业管理费用
     *
     * @return
     */
    @RequestMapping("/toPropertyFee")
    public String toPropertyFee(HttpServletRequest request, ModelMap map) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Property> properties = costService.findOneProperty(user.getR_username());
        for (Property property : properties) {
            property.setProp_date_str(DateFormat.dateToString(property.getProp_date(), "yyyy-MM"));
            String addressName = addressService.findAddressNameById(property.getH_id());
            property.setH_name(addressName);
        }
        map.addAttribute("list", properties);
        return "user/property";
    }

    /**
     * 物业管理费按月查询
     */
    @RequestMapping("/findByMonth")
    public String toFindPropFeeByMonth(String month, ModelMap map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Property> properties = costService.findPropByMonth(month, user.getR_username());
        for (Property property : properties) {
            property.setProp_date_str(DateFormat.dateToString(property.getProp_date(), "yyyy-MM"));
            String addressName = addressService.findAddressNameById(property.getH_id());
            property.setH_name(addressName);
        }
        map.addAttribute("list", properties);
        return "user/property";
    }


    /**
     * 前往查询生活费用页面
     *
     * @return
     */
    @RequestMapping("/empToMaintenance")
    public String empToMaintenance() {
        return "emp/emp_maintenance";
    }


    /**
     * 员工查询业主水电费用（以房屋为单位）
     */
    @RequestMapping("/mFindById.do")
    public String maintenanceFindbyHid(ModelMap modelMap, String one, String two, String three, String four) {
        String h_id = one + two + three + four;
        List<Maintenance> maintenances = costService.findMaintenanceByHid(h_id);
        for (Maintenance list : maintenances) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setH_id(h_id);
            list.setDate_str(DateFormat.dateToString(list.getDate(), "yyyy-MM-dd"));
        }
        modelMap.addAttribute("list", maintenances);
        return "emp/emp_maintenance";
    }



    @RequestMapping("/updateMaintenance.do")
    public String maintenanceDelivered(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "hid", required = true) String hid,@RequestParam(value = "mode", required = true) int mode, ModelMap modelMap) {
        costService.updateStatus(id,mode);
        List<Maintenance> maintenances = costService.findMaintenanceByHid(hid);
        for (Maintenance list : maintenances) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setDate_str(DateFormat.dateToString(list.getDate(), "yyyy-MM-dd"));
        }
        modelMap.addAttribute("list", maintenances);
        return "emp/emp_maintenance";
    }

    @RequestMapping("/delMaintenance.do")
    public String delMaintenance(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "hid", required = true) String hid,ModelMap modelMap) {
        costService.delMaintenance(id);

        List<Maintenance> maintenances = costService.findMaintenanceByHid(hid);
        for (Maintenance list : maintenances) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setDate_str(DateFormat.dateToString(list.getDate(), "yyyy-MM-dd"));
        }
        modelMap.addAttribute("list", maintenances);
        return "emp/emp_maintenance";
    }


    /**
     * 前往查询停车费用页面
     *
     * @return
     */
    @RequestMapping("/empToParkFee")
    public String empToParkFee() {
        return "emp/emp_parkfee";
    }

    /**
     * 查询车辆月保信息
     *
     * @return
     */
    @RequestMapping("/carFindById.do")
    public String parkfeeFindById(String province, String number, ModelMap map) {
        String id = province + number;
        ParkFee parkFee = costService.findParkFeeByCarId(id);
        Date date = new Date();
        int days = (int) ((parkFee.getExpire().getTime() - date.getTime()) / (24 * 3600 * 1000)) + 1;
        if (days < 0) {
            days = 0;
            costService.setParkfeeArrearage(parkFee.getCar_id());
        }
        parkFee.setExpire_str(DateFormat.dateToString(parkFee.getExpire(), "yyyy-MM-dd"));
        parkFee.setBalance(days);
        parkFee.setPlaceStr(parkFee.getPlace()==0?"室外":"室内");
        map.addAttribute("list", parkFee);
        return "emp/emp_parkfee";
    }


    /**
     * 前往查询生活费用页面
     *
     * @return
     */
    @RequestMapping("/empToProperty")
    public String empToProperty() {
        return "emp/emp_property";
    }

    /**
     * 查询物业费用信息
     *
     * @return
     */
    @RequestMapping("/pFindById.do")
    public String propertyFindById(ModelMap modelMap, String one, String two, String three, String four) {
        String hid = one + two + three + four;
        List<Property> property = costService.findPropertyByHid(hid);
        for (Property list : property) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setProp_date_str(DateFormat.dateToString(list.getProp_date(), "yyyy-MM-dd"));
        }
        modelMap.addAttribute("list", property);
        return "emp/emp_property";
    }

    @RequestMapping("/updateProp.do")
    public String propDelivered(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "hid", required = true) String hid,@RequestParam(value = "mode", required = true) int mode,ModelMap map) {
        costService.updatePropStatus(id,mode);
        List<Property> property = costService.findPropertyByHid(hid);
        for (Property list : property) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setProp_date_str(DateFormat.dateToString(list.getProp_date(), "yyyy-MM-dd"));
        }
        map.addAttribute("list", property);
        return "emp/emp_property";
    }

    @RequestMapping("/delProp.do")
    public String delProp(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "hid", required = true) String hid,ModelMap modelMap) {
        costService.delProp(id);
        List<Property> property = costService.findPropertyByHid(hid);
        for (Property list : property) {
            String addressName = addressService.findAddressNameById(list.getH_id());
            list.setH_name(addressName);
            list.setProp_date_str(DateFormat.dateToString(list.getProp_date(), "yyyy-MM-dd"));
        }
        modelMap.addAttribute("list", property);
        return "emp/emp_property";
    }

    /**
     * 员工前往设置业主生活费用账单
     */
    @RequestMapping("/empToSetMaintenanceManagement")
    public String empToSetMaintenanceManagement(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CostUnit electricUnit = costService.findElectricUnit();
        CostUnit waterUnit = costService.findWaterUnitUnit();
        CostUnit propertyUnit = costService.findPropertyUnit();
        CostUnit gasUnit = costService.findGasUnit();
        session.setAttribute("elec", electricUnit);
        session.setAttribute("water", waterUnit);
        session.setAttribute("prop", propertyUnit);
        session.setAttribute("gas", gasUnit);
        return "emp/emp_fee_maintenance";
    }

    /**
     * 员工设置业主生活费用账单
     */
    @RequestMapping("/empSetMainFee.do")
    public String empSetMaintenanceManagement(String date, String one, String two, String three, String four, String elecv, String elecfee, String waterv, String waterfee,  String gasv, String gasfee, ModelMap map) throws ParseException {
        String hid = one + two + three + four;
        Date dateTime = DateFormat.stringToDate(date);
        if (!elecfee.isEmpty() || !waterfee.isEmpty() || !gasfee.isEmpty()) {
            costService.setMaintenanceByHidInDate(hid, elecfee, waterfee, gasfee, elecv, waterv, gasv, dateTime);
            map.addAttribute("flag", "操作成功");
            return "emp/emp_fee_maintenance";
        } else {
            map.addAttribute("flag", "必须填写下列费用其中一项");
            return "emp/emp_fee_maintenance";
        }
    }

    /**
     * 员工前往设置业主物业费账单
     */
    @RequestMapping("/empToSetPropertyManagement")
    public String empToPropertyManagement(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CostUnit propertyUnit = costService.findPropertyUnit();
        session.setAttribute("prop", propertyUnit);
        return "emp/emp_fee_property";
    }


    /**
     * 员工设置业主生活费用账单
     */
    @RequestMapping("/empSetPropertyFee.do")
    public String empToSetPropertyManagement(String date, String one, String two, String three, String four, String propfee, ModelMap map) throws ParseException {
        String hid = one + two + three + four;
        Date dateTime = DateFormat.stringToDate(date);
        costService.setPropertyByHidInDate(hid, propfee, dateTime);
        map.addAttribute("flag", "操作成功");
        return "emp/emp_fee_property";

    }


    /**
     * 管理员前往设置费用单位
     */
    @RequestMapping("/adminToFeeManagement")
    public String adminToFeeManagement(ModelMap map,@RequestParam(value = "flag",required = false) String flag) {
        CostUnit electricUnit = costService.findElectricUnit();
        CostUnit waterUnit = costService.findWaterUnitUnit();
        CostUnit propertyUnit = costService.findPropertyUnit();
        CostUnit gasUnit = costService.findGasUnit();

        map.addAttribute("flag", flag);
        map.addAttribute("elec", electricUnit);
        map.addAttribute("water", waterUnit);
        map.addAttribute("prop", propertyUnit);
        map.addAttribute("gas", gasUnit);
        return "admin/admin_fee_management";
    }

    /**
     * 管理员设置费用单位
     */
    @RequestMapping("/feeManagement.do")
    public String feeManagement(String elec_one, String elec_two, String elec_three, String water_one, String water_two, String water_three, String property, String gas) {
        try {
            if (elec_one.isEmpty()&&elec_two.isEmpty()&&elec_three.isEmpty()&&water_one.isEmpty()&&water_two.isEmpty()&&water_three.isEmpty()&&property.isEmpty()&&gas.isEmpty()){
                return "redirect:/cost/adminToFeeManagement?flag=fail";
            }else {
                System.out.println(elec_one+ elec_two+elec_three+water_one+water_two+water_three+property+gas);
                costService.setFeeUnit(elec_one, elec_two, elec_three, water_one, water_two, water_three, property, gas);
                return "redirect:/cost/adminToFeeManagement?flag=success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/cost/adminToFeeManagement?flag=fail";
        }
    }
}
