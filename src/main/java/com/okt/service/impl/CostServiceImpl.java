package com.okt.service.impl;

import com.okt.dao.CostDao;
import com.okt.entity.CostUnit;
import com.okt.entity.Maintenance;
import com.okt.entity.ParkFee;
import com.okt.entity.Property;
import com.okt.service.CostService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
@Service
public class CostServiceImpl implements CostService {
    @Autowired
    CostDao costDao;

    @Override
    public List<Maintenance> findOneMaintenance(String username) {
        return costDao.findOneMaintenance(username);
    }

    @Override
    public List<ParkFee> findOneParkFee(String r_username) {
        return costDao.findOneParkFee(r_username);
    }

    @Override
    public List<Property> findOneProperty(String username) {
        return costDao.findOneProperty(username);
    }

    @Override
    public List<Maintenance> findMaintenanceByHid(String h_id) {
        return costDao.findMaintenanceByHid(h_id);
    }

    @Override
    public void updateStatus(String id,int mode) {
        costDao.updateStatus(id,mode);
    }

    @Override
    public ParkFee findParkFeeByCarId(String id) {
        return costDao.findOneParkFeeByCarId(id);
    }

    @Override
    public List<Property> findPropertyByHid(String h_id) {
        return costDao.findPropertyByHid(h_id);
    }

    @Override
    public List<Property> findPropByMonth(String month,String username) {
        return costDao.findPropertyByMonth(month,username);
    }

    @Override
    public List<Maintenance> findMainByMonth(String month, String r_username) {
        return costDao.findMainByMonth(month, r_username);
    }

    @Override
    public void setParkfeeArrearage(String car_id) {
        costDao.setParkfeeArrearage(car_id);
    }

    @Override
    public void resetParkfeeDate(Date date,String car_id) {
        costDao.setParkfeeExpire(date,car_id);
    }

    @Override
    public void renewParkfee(String carid, int mode,int place) {
        costDao.renewParkfee(carid,mode,place);
    }

    @Override
    public void updatePropStatus(String id,int mode) {
        costDao.updatePropStatus(id,mode);
    }

    @Override
    public CostUnit findElectricUnit() {
        return costDao.findElecUnit();
    }

    @Override
    public CostUnit findWaterUnitUnit() {
        return costDao.findWaterUnit();
    }

    @Override
    public CostUnit findPropertyUnit() {
        return costDao.findPropUnit();
    }

    @Override
    public CostUnit findGasUnit() {
        return costDao.findGasUnit();
    }

    @Override
    public void setFeeUnit(String elec_one, String elec_two, String elec_three, String water_one, String water_two, String water_three, String property,String gas) {
        if(!(elec_one.isEmpty()&&elec_two.isEmpty()&&elec_three.isEmpty())){
            costDao.updateUnit(Double.parseDouble(elec_one.isEmpty()?"0":elec_one), Double.parseDouble(elec_two.isEmpty()?"0":elec_two), Double.parseDouble(elec_three.isEmpty()?"0":elec_three), 1);
        }
         if(!(water_one.isEmpty()&&water_two.isEmpty()&&water_three.isEmpty())){
            costDao.updateUnit(Double.parseDouble(water_one.isEmpty()?"0":water_one), Double.parseDouble(water_two.isEmpty()?"0":water_two),Double.parseDouble(water_three.isEmpty()?"0":water_three), 2);
        }
        if(!(property.isEmpty())){
            costDao.updateUnit(Double.parseDouble(property), 0, 0, 3);
        }
        if(!(gas.isEmpty())){
            costDao.updateUnit(Double.parseDouble(gas), 0, 0, 4);
        }
    }

    @Override
    public void setPropertyByHidInDate(String hid,String propfee,Date date) {
        System.out.println("进来了管理费业务");
        String year = DateFormat.dateToString(date, "yyyy");
        String month=DateFormat.dateToString(date, "MM");
        System.out.println("年："+year+"月："+month);
        System.out.println("房屋编号:"+hid);
        Property property = costDao.findPropertyByHidInDate(hid, year, month);
        if(property!=null){
            System.out.println("该月存在了");
            costDao. updatePropertyByPid(property.getProp_id(),propfee);
        }
        else{
            costDao.addProperty("P"+DateFormat.dateToString(new Date(), "yyyyMMddHHmmss"),propfee,date,hid);
        }
    }

    @Override
    public void delMaintenance(String id) {
        costDao.delMaintenanceById(id);
    }

    @Override
    public void delProp(String id) {
        costDao.delPropById(id);
    }

    @Override
    public void delUserCar(String id) {
        costDao.delUserCarById(id);
    }

    @Override
    public void updateUserCar(String id, String cid,String carname) {
        costDao.updateCarNumber(id,cid,carname);
    }

    @Override
    public boolean findCarByOther(String id) {
        System.out.println(id);
        ParkFee parkFee=costDao.findOneParkFeeByCarId(id);
        if(parkFee!=null){
            return true;
        }
       else {
           return false;
        }
    }

    @Override
    public void setMaintenanceByHidInDate(String hid,String elecfee, String waterfee,String gasfee,String elecv,String waterv,String gasv,Date date) {
        System.out.println("进来了生活费业务");
        String year = DateFormat.dateToString(date, "yyyy");
        String month=DateFormat.dateToString(date, "MM");
        System.out.println("年："+year+"月："+month);
        System.out.println("房屋编号:"+hid);
        Maintenance maintenance = costDao.findMaintenanceByHidInDate(hid,year,month);
        if(maintenance!=null){
            costDao.updateMaintenanceByMid(maintenance.getId(),elecv.isEmpty()?null:elecv,elecfee.isEmpty()?null:elecfee,waterv.isEmpty()?null:waterv,waterfee.isEmpty()?null:waterfee,gasv.isEmpty()?null:gasv,gasfee.isEmpty()?null:gasfee);
        }else {
            costDao.addMaintenance("M"+DateFormat.dateToString(new Date(), "yyyyMMddHHmmss"),hid,elecv, elecfee, waterv, waterfee, gasv,gasfee,date);
        }
    }


}
