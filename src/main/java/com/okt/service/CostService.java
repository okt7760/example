package com.okt.service;

import com.okt.entity.CostUnit;
import com.okt.entity.Maintenance;
import com.okt.entity.ParkFee;
import com.okt.entity.Property;


import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
public interface CostService {

    List<Maintenance> findOneMaintenance(String username);

    List<ParkFee> findOneParkFee(String r_username);

    List<Property> findOneProperty(String username);

    List<Maintenance> findMaintenanceByHid(String h_id);

    void updateStatus(String id,int mode);

    ParkFee findParkFeeByCarId(String id);

    List<Property> findPropertyByHid(String h_id);

    List<Property> findPropByMonth(String month,String username);

    List<Maintenance> findMainByMonth(String month, String r_username);

    void setParkfeeArrearage(String car_id);

    void resetParkfeeDate(Date date,String car_id);

    void renewParkfee(String carid, int mode,int place);

    void updatePropStatus(String id,int mode);

    CostUnit findElectricUnit();

    CostUnit findWaterUnitUnit();

    CostUnit findPropertyUnit();

    CostUnit findGasUnit();

    void setFeeUnit(String elec_one, String elec_two, String elec_three, String water_one, String water_two, String water_three, String property,String gas);


    void setMaintenanceByHidInDate(String hid,String elecfee, String waterfee,String gasfee,String elecv,String waterv,String gasv,Date date);

    void setPropertyByHidInDate(String hid, String propfee, Date date);

    void delMaintenance(String id);

    void delProp(String id);

    void delUserCar(String id);

    void updateUserCar(String id, String cid,String carname);

    boolean findCarByOther(String id);
}
