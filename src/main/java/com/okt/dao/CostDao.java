package com.okt.dao;

import com.okt.entity.CostUnit;
import com.okt.entity.Maintenance;
import com.okt.entity.ParkFee;
import com.okt.entity.Property;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
@Mapper
public interface CostDao {
    /**
     * 查询当前用户所有生活用费
     * @returne
     */
    @Select("select  * from maintenance where h_id in (select h_id from house_user where r_username = #{username}) order by date desc")
    List<Maintenance> findOneMaintenance(String username);

    /**
     * 查询当前用户车辆月保状况
     */
    @Select("select  * from parkfee where r_username = #{username}")
    List<ParkFee> findOneParkFee(String username);

    /**
     * 查询当前用户房产物业费用
     */
    @Select("select  * from property where h_id in (select h_id from house_user where r_username = #{username}) order by prop_date desc")
    List<Property> findOneProperty(String username);

    /**
     * 查询某个楼层费用（多重搜索）
     * @return
     */
    @Select("select * from  maintenance where h_id = #{h_id} order by date desc")
    List<Maintenance> findMaintenanceByHid(String h_id);

    /**
     * 查询某个楼层费用（多重搜索）
     * @return
     */
    @Select("select * from  property where h_id = #{h_id} order by prop_date desc")
    List<Property> findPropertyByHid(String h_id);

    @Update("update maintenance set status = #{mode} where id = #{id}")
    void updateStatus(@Param("id") String id,@Param("mode") int mode);

    @Update("update property set prop_status = #{mode} where prop_id = #{id}")
    void updatePropStatus(@Param("id") String id,@Param("mode") int mode);

    @Select("select  * from parkfee where car_id = #{id}")
    ParkFee findOneParkFeeByCarId(String id);

    @Select("select * from property where prop_date like '%-${month}-%' and  h_id in (select h_id from house_user where r_username = #{username}) order by prop_date desc")
    List<Property> findPropertyByMonth(@Param("month") String month,@Param("username") String username);

    @Select("select * from maintenance where date like '%-${month}-%' and  h_id in (select h_id from house_user where r_username = #{username}) order by date desc")
    List<Maintenance> findMainByMonth(@Param("month") String month,@Param("username") String username);

    @Update("update parkfee set status = 0 where car_id = #{car_id}")
    void setParkfeeArrearage(String car_id);

    @Update("update parkfee set expire=#{date},status=1 where car_id = #{car_id}")
    void setParkfeeExpire(@Param("date") Date date,@Param("car_id") String car_id);

    @Update("update parkfee set expire=date_add(expire,interval #{mode} MONTH),place = #{place},status=1 where car_id = #{car_id}")
    void renewParkfee(@Param("car_id") String carid, @Param("mode") int mode,@Param("place") int place);

    @Select("select  * from cost where id = 1")
    CostUnit findElecUnit();

    @Select("select  * from cost where id = 2")
    CostUnit findWaterUnit();

    @Select("select  * from cost where id = 3")
    CostUnit findPropUnit();

    @Select("select  * from cost where id = 4")
    CostUnit findGasUnit();

    @Update("<script>update cost " +
            "<set> " +
            "<if test='one != 0'> one = #{one},</if>" +
            "<if test='two != 0'> two = #{two},</if>" +
            "<if test='three != 0'> three = #{three},</if>" +
            "</set> " +
            "where id = #{id}</script>")
    void updateUnit(@Param("one") double one,@Param("two")double two,@Param("three")double three,@Param("id") int id);

    /**
     * 根据房屋ID是否存在输入年-月份的水电数据
     */
    @Select("select * from maintenance where h_id = #{hid} and date like '${year}-${month}-%'")
    Maintenance findMaintenanceByHidInDate(@Param("hid") String hid,@Param("year") String year,@Param("month") String month);

    /**
     * 根据房屋ID是否存在输入年-月份的物业数据
     */
    @Select("select * from property where h_id = #{hid} and prop_date like '${year}-${month}-%'")
    Property findPropertyByHidInDate(@Param("hid") String hid,@Param("year") String year,@Param("month") String month);

    /**
     * 更新该月房屋水电
     */
    @Update("<script>update maintenance " +
            "<set> " +
            "<if test='elecv != null and elecv != \" \" '> electric_v = #{elecv},</if>" +
            "<if test='elecfee != null and elecfee != \" \" '> electric_fee = #{elecfee},</if>" +
            "<if test='waterv != null and waterv != \" \" '> water_v = #{waterv},</if>" +
            "<if test='waterfee != null and waterfee != \" \" '> water_fee = #{waterfee},</if>" +
            "<if test='gasv != null and gasv != \" \" '> gas_v = #{gasv},</if>" +
            "<if test='gasfee != null and gasfee != \" \" '> gas_fee = #{gasfee},</if>" +
            "</set> " +
            "where id = #{mid}</script>")
    void updateMaintenanceByMid(@Param("mid") String mid,@Param("elecv") String elecv,@Param("elecfee") String elecfee,@Param("waterv") String waterv,@Param("waterfee") String waterfee,@Param("gasv") String gasv,@Param("gasfee")String gasfee);

    /**
     * 根据查询到该月的物业管理费进行更新
     */
    @Update("update property set prop_fee = #{propfee} where prop_id = #{pid}")
    void updatePropertyByPid(@Param("pid") String pid,@Param("propfee") String propfee);
    /**
     * 根据房屋ID插入当月生活费
     */
    @Insert({
            "<script>" ,
            "insert into maintenance(id,h_id,",
            "<if test='waterv != null and waterv != \" \" '>",
            "water_v,",
            "</if>",
            "<if test='waterfee != null and waterfee != \" \" '>",
            "water_fee,",
            "</if>",
            "<if test='elecv != null and elecv != \" \" '>",
            "electric_v,",
            "</if>",
            "<if test='elecfee != null and elecfee != \" \" '>",
            "electric_fee,",
            "</if>",
            "<if test='gasv != null and gasv != \" \" '>",
            "gas_v,",
            "</if>",
            "<if test='gasfee != null and gasfee != \" \" '>",
            "gas_fee,",
            "</if>",
            "date,status) value(#{id},#{hid},",
            "<if test='waterv != null and waterv != \" \" '>",
            "#{waterv},",
            "</if>",
            "<if test='waterfee != null and waterfee != \" \" '>",
            "#{waterfee},",
            "</if>",
            "<if test='elecv != null and elecv != \" \" '>",
            "#{elecv},",
            "</if>",
            "<if test='elecfee != null and elecfee != \" \" '>",
            "#{elecfee},",
            "</if>",
            "<if test='gasv != null and gasv != \" \" '>",
            "#{gasv},",
            "</if>",
            "<if test='gasfee != null and gasfee != \" \" '>",
            "#{gasfee},",
            "</if>",
            "#{date},0)",
            "</script>"
    })
   void addMaintenance(@Param("id") String id,@Param("hid") String hid,@Param("elecv")String elecv,@Param("elecfee")String elecfee,@Param("waterv")String waterv,@Param("waterfee")String waterfee,@Param("gasv")String gasv,@Param("gasfee")String gasfee,@Param("date")Date date);

    /**
     * 根据房屋id插入该月物业费用
     */
    @Insert("insert into property values(#{pid},#{hid},#{date},#{propfee},0)")
   void addProperty(@Param("pid") String pid,@Param("propfee") String propfee,@Param("date") Date date,@Param("hid") String hid);

    @Delete("delete from maintenance where id = #{id}")
    void delMaintenanceById(String id);

    @Delete("delete from property where prop_id = #{id}")
    void delPropById(String id);

    @Delete("delete from parkfee where car_id = #{id}")
    void delUserCarById(String id);

    @Update("update parkfee set car_id=#{id},car_name=#{carname} where car_id =#{cid}")
    void updateCarNumber(@Param("id") String id, @Param("cid")String cid, @Param("carname")String carname);
}
