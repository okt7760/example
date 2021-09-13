package com.okt.controller;

import com.github.pagehelper.PageHelper;
import com.okt.entity.*;
import com.okt.service.AddressService;
import com.okt.service.CostService;
import com.okt.service.UserService;
import com.okt.util.DateFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/23
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/userManage")
public class UserManageControlller {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    CostService costService;

    @RequestMapping("/toUserManagement")
    public String toUserManagement(){
        return "emp/emp_usermanagement";
    }

    @RequestMapping("findUserByName.do")
    public String findUserByName(String name,ModelMap map){
       List<User> userList= userService.findUserByName(name);
       map.addAttribute("list",userList);
       return "emp/emp_usermanagement";
    }
    /**
     * 前往用户详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toUpdate")
    public String empToUpdate(@RequestParam(value = "id",required = true) String id,ModelMap map,@RequestParam(value = "flag",required = false) String flag){
        User user = userService.findOne(id);
        List<ParkFee> oneParkFee = costService.findOneParkFee(id);
        for (ParkFee parkFee : oneParkFee) {
            parkFee.setExpire_str(DateFormat.dateToString(parkFee.getExpire(), "yyyy-MM-dd"));
        }
        map.addAttribute("user", user);
        map.addAttribute("carfee", oneParkFee);
        map.addAttribute("flag", flag);
        return "emp/emp_usermanagement_update";
    }

    /**
     * 删除用户
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/del.do")
    public String delUser(@RequestParam(value = "id",required = true) String id,ModelMap map){
        userService.deleteUser(id);
        return "redirect:/userManage/toUserManagement";
    }

    /**
     * 更新用户
     * @param name
     * @param uid
     * @param phone
     * @param gender
     * @return
     */
    @RequestMapping("/update.do")
    public String updateUser(String username,String name,String uid,String phone,String gender){

        if(userService.checkUserId(uid,username)){
            return "redirect:/userManage/toUpdate?id="+username+"&flag=0 ";
        }
        userService.updateUser(username,name, uid, phone,gender);
        return "redirect:/userManage/toUpdate?id="+username+"&flag=1";
    }

    /**
     * 删除房屋信息
     * @return
     */
    @RequestMapping("/delUserAdd.do")
    public String empDelUserAdd(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "uid",required = true) String uid){
        userService.delUserAddress(id,uid);
        return "redirect:/userManage/toUpdate?id="+uid;
    }

    /**
     * 前往用户住址详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toUpdateUserAdd")
    public String toEmpToUpdateAddress(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "uid",required = true) String uid,ModelMap map){
        List<Address> allOneAddress = addressService.findAllOneAddress();
        List<Address> allTwoAddress = addressService.findAllTwoAddress();
        List<Address> allThreeAddress = addressService.findAllThreeAddress();
        List<Address> allFourAddress = addressService.findAllFourAddress();
        map.addAttribute("one",allOneAddress );
        map.addAttribute("two",allTwoAddress );
        map.addAttribute("three",allThreeAddress );
        map.addAttribute("four",allFourAddress );
        map.addAttribute("aid", id);
        map.addAttribute("uid", uid);
        return "emp/emp_usermanagement_updateadd";
    }

    /**
     * 提交地址修改
     */
    @RequestMapping("/updateUserAdd.do")
    public String empToUpdateAddress( String aid, String uid,String addOne, String addTwo, String addThree, String addFour){
        String newAid= addOne+addTwo+addThree+addFour;
        if(aid.equals(newAid)){
            return "redirect:/userManage/toUpdateUserAdd?id="+aid+"&uid="+uid+"&flag=same";
        }
        else {
            String firstName=addressService.findAddressOneNameById(addOne);
            String secondName=addressService.findAddressTwoNameById(addTwo);
            String thirdName=addressService.findAddressThreeNameById(addThree);
            String fourthName=addressService.findAddressFourNameById(addFour);
            addressService.updateAddress(aid,uid, addOne+addTwo+addThree+addFour,firstName,secondName,thirdName,fourthName);
            return "redirect:/userManage/toUpdate?id="+uid;
        }
    }

    /**dd
     * 前往添加房产信息
     */
    @RequestMapping("/toAddUserAddr")
    public String toEmpToAddAddress(@RequestParam(value = "uid",required = true) String uid,ModelMap map){
        List<Address> allOneAddress = addressService.findAllOneAddress();
        List<Address> allTwoAddress = addressService.findAllTwoAddress();
        List<Address> allThreeAddress = addressService.findAllThreeAddress();
        List<Address> allFourAddress = addressService.findAllFourAddress();
        map.addAttribute("one",allOneAddress );
        map.addAttribute("two",allTwoAddress );
        map.addAttribute("three",allThreeAddress );
        map.addAttribute("four",allFourAddress );
        map.addAttribute("uid", uid);
        return "emp/emp_usermanagement_addaddr";
    }


    /**
     * 添加房产信息
     */
    @RequestMapping("/addUserAdd.do")
    public String empToAddAddress(String uid,String addOne, String addTwo, String addThree, String addFour){
        String hid = addOne+addTwo+addThree+addFour;
        UserAndAddress userAndAddress=addressService.findUserAndAddressByUidAndHid(hid,uid);
        if(userAndAddress!=null){
            return "redirect:/userManage/toAddUserAddr?uid="+uid+"&flag='exist'";
        }
        String firstName=addressService.findAddressOneNameById(addOne);
        String secondName=addressService.findAddressTwoNameById(addTwo);
        String thirdName=addressService.findAddressThreeNameById(addThree);
        String fourthName=addressService.findAddressFourNameById(addFour);
        addressService.addAddressAndUser(new UserAndAddress(hid,uid,firstName,secondName,thirdName,fourthName));
        return "redirect:/userManage/toUpdate?id="+uid;
    }

    @RequestMapping("/delUserCar.do")
    public String delUserCar(@RequestParam(value = "id",required = true)String id,@RequestParam(value = "uid",required = true) String uid){
        costService.delUserCar(id);
        return "redirect:/userManage/toUpdate?id="+uid;
    }

    @RequestMapping("/toUpdateUserCar")
    public String toUpdateUserCar(@RequestParam(value = "id",required = true)String id,@RequestParam(value = "uid",required = true) String uid,@RequestParam(value = "flag",required = false) String flag,ModelMap map){
        map.addAttribute("carid", id);
        map.addAttribute("userid",uid);
        map.addAttribute("flag",flag);
        return "emp/emp_usermanagement_updatecar";
    }

    @RequestMapping("/updateUserCar.do")
    public String updateUserCar(String province,String number,String uid,String cid){
        String id=province+number;
        if(id.equals(cid)){
            return "redirect:/userManage/toUpdateUserCar?id="+cid+"&uid="+uid+"&flag=1";
        }
        else if(costService.findCarByOther(id)) {
            return "redirect:/userManage/toUpdateUserCar?id="+cid+"&uid="+uid+"&flag=2";
        }
        else {
            String carName=userService.getCarName(province,number);
            costService.updateUserCar(id, cid,carName);
            return "redirect:/userManage/toUpdate?id=" + uid;
        }
    }

    /**
     * 审核操作（空指针捕获）
     * @return
     */
    @RequestMapping("/toAudit")
    public String toAudit(ModelMap map) {

        List<User> allUnauthorizedUser = userService.findAllUnauthorizedUser();
        for (User user : allUnauthorizedUser) {
            user.setH_name(addressService.findAddressNameByUsername(user.getR_username()));
        }
        map.addAttribute("list", allUnauthorizedUser);
        return "emp/emp_usermanagement_audit";

    }
    /**
     * 审核操作
     * @return
     */
    @RequestMapping("/audit.do")
    public String audit(@RequestParam(value = "id",required = true) String id,@RequestParam(value = "mode",required = true) int mode){
        userService.updateUserStatus(id, mode);
        return "redirect:/userManage/toAudit";
    }

    /**
     * 前往车辆绑定
     */
    @RequestMapping("/toBandCar")
    public String toCarBand(){
        return "emp/emp_usercar_band";
    }

    /**
     * 车辆绑定
     */
    @RequestMapping("/band.do")
    public String Band(String id,String province,String number,String place,ModelMap map){
        User user = userService.findByUserId(id);
        if(user!=null){
            String carId=province+number;
            if(costService.findParkFeeByCarId(carId)==null){
                String carName=userService.getCarName(province,number);
                userService.bandCar(carId,carName,user.getR_username(),Integer.parseInt(place));
                map.addAttribute("flag", "绑定成功");
                return "emp/emp_usercar_band";
            }
            else {
                map.addAttribute("flag", "该车辆已被绑定");
                return "emp/emp_usercar_band";
            }

        }
        else{
            map.addAttribute("flag", "该用户不存在，无法绑定");
            return "emp/emp_usercar_band";
        }

    }

    /**
     * 车辆续费
     */
    @RequestMapping("/toRenew")
    public String toRenew(){
        return "emp/emp_usercar_renew";
    }

    /**
     * 车辆续费(1,3,6,12)月份选择
     */
    @RequestMapping("/renew.do")
    public String renew(String id,String province,String number,int mode,String place,HttpServletRequest request){
            User user = userService.findByUserId(id);
            String carid=province+number;
            ParkFee car = costService.findParkFeeByCarId(carid);
            if(user==null){
                request.setAttribute("flag","该用户不存在");
                return "emp/emp_usercar_renew";
            }
            else if(car==null){
                request.setAttribute("flag","该车辆不存在");
                return "emp/emp_usercar_renew";
            }
            else if(car.getStatus()==0&&car.getR_username().equals(user.getR_username())){
                System.out.println("新续费");
                Date date = new Date();
                Calendar rightNow= Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.MONTH, mode);
                date=rightNow.getTime();
                costService.resetParkfeeDate(date,carid);
                request.setAttribute("flag","续费成功，本次续费为"+mode+"月");
                return "emp/emp_usercar_renew";
            }
            else if(car.getR_username().equals(user.getR_username())){
                System.out.println("旧续费");
                costService.renewParkfee(carid,mode,Integer.parseInt(place));
                request.setAttribute("flag","续费成功，本次续费为"+mode+"月");
                return "emp/emp_usercar_renew";
            }
            else{
                request.setAttribute("flag","该用户未绑定此车辆");
                return "emp/emp_usercar_renew";
            }
    }



    /**
     * 前往员工管理
     */
    @RequestMapping("/toAllEmp")
    public String toAllEmp(ModelMap map){
        List<Empolyee> empolyees=userService.findAllEmp();
        map.addAttribute("list", empolyees);
        return "admin/admin_management";
    }

    /**
     * 员工详情
     */
    @RequestMapping("/toEmpDetail")
    public String toEmpDetail(@RequestParam("id")String id,ModelMap map,@RequestParam(value = "flag",required = false) String flag){
        Empolyee empolyee=userService.findEmp(id);
        map.addAttribute("emp", empolyee);
        map.addAttribute("flag", flag);
        return "admin/admin_management_detail";
    }
    /**
     * 员工更新
     */
    @RequestMapping("/empUpdate.do")
    public String empUpdate(String username,String name,String uid,String phone,int gender){
       if(userService.checkEmpId(uid,username)){
            return "redirect:/userManage/toEmpDetail?id="+username+"&flag=exist";
        }
        else {
            userService.updateEmp(username,name,uid,phone,gender);
            return "redirect:/userManage/toEmpDetail?id="+username+"&flag=success";
        }
    }
    /**
     * 员工删除
     */
    @RequestMapping("/empDel")
    public String empDel(@Param("id")String id){
        userService.deleteEmp(id);
        return "redirect:/userManage/toAllEmp";
    }

    /**
     * 前往添加员工
     */
    @RequestMapping("/toAddEmp")
    public String toAddEmp(){
        return "admin/admin_management_add";
    }

    /**
     * 添加员工操作
     */
    @RequestMapping("addEmp.do")
    public String addEmp(String username,String name,String uid,String phone,int gender,ModelMap map){
        if(userService.findEmp(username)!=null){
            map.addAttribute("flag", "该用户已存在");
            return "admin/admin_management_add";
        }
        else if(userService.checkEmpId(uid,username)){
            map.addAttribute("flag", "该身份证号已存在");
            return "admin/admin_management_add";
        }
        userService.addEmp(username,name,uid,phone,gender);
        return "redirect:/userManage/toAllEmp";
    }


}
