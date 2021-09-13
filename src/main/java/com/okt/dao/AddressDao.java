package com.okt.dao;


import com.okt.entity.Address;
import com.okt.entity.UserAndAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by obeeey on 2021/3/15
 * 即使再小的帆也能远航
 */
@Mapper
public interface AddressDao {



    @Select("select * from house_user where h_id = #{h_id}")
    UserAndAddress findAddressNameById(String h_id);

    /**
     * 根据帐号名查询用户名下房产
     * @param username
     * @return
     */
    @Select("select * from house_user where r_username = #{username}")
    UserAndAddress getAddressByUserName(@Param("username") String username);

    /**
     * 绑定用户和房产
     * @param address
     */
    @Insert("insert into house_user values(#{h_id},#{r_username},#{h_id_one},#{h_id_two},#{h_id_three},#{h_id_four})")
    void addAddressAndUser(UserAndAddress address);

    /**
     * 寻找一号地址名
     * @param id
     * @return
     */
    @Select("select name from house_one where h_id = #{id} ")
    String findAddressOne(String id);

    /**
     * 寻找二号地址名
     * @param id
     * @return
     */
    @Select("select name from house_two where h_id = #{id} ")
    String findAddressTwo(String id);

    /**
     * 寻找三号地址名
     * @param id
     * @return
     */
    @Select("select name from house_three where h_id = #{id} ")
    String findAddressThree(String id);

    /**
     * 寻找四号地址名
     * @param id
     * @return
     */
    @Select("select name from house_four where h_id = #{id} ")
    String findAddressFour(String id);

    /**
     * 寻找所有一号地址位
     * @return
     */
    @Select("select * from house_one")
    List<Address> findAllOneAddress();

    /**
     * 寻找所有二号地址位
     * @return
     */
    @Select("select * from house_two")
    List<Address> findAllTwoAddress();

    /**
     * 寻找所有三号地址位
     * @return
     */
    @Select("select * from house_three")
    List<Address> findAllThreeAddress();

    /**
     * 寻找所有四号地址位
     * @return
     */
    @Select("select * from house_four")
    List<Address> findAllFourAddress();

    /**
     * 添加地址位
     * @param name
     */
    @Insert("insert into ${tableName} values (#{id},#{name})")
    void addAddress(@Param("tableName")String tableName,@Param("id") String id,@Param("name") String name);

    @Select("select * from house_user where h_id=#{id} and user_id=#{uid}")
    UserAndAddress findAddressById(@Param("id")String id, @Param("uid")String uid);

    @Update("update house_user set h_id=#{hid},h_id_one=#{one},h_id_two=#{two},h_id_three=#{three},h_id_four=#{four} where h_id = #{aid} and r_username = #{uid}")
    void updateAddress(@Param("aid")String aid,@Param("uid") String uid, @Param("hid")String hid, @Param("one")String one,@Param("two") String two,@Param("three") String three, @Param("four")String four);

    /**
     *
     * @param username
     */
    @Delete("delete from house_user where r_username = #{username} ")
    void delUserAndHourse(String username);

    @Delete("delete from ${house} where h_id = #{id}")
    void delAddress(@Param("house") String house, @Param("id") String id);

    @Select("select * from house_user where r_username = #{uid} and h_id = #{hid}")
    UserAndAddress findUserAndAddressByUidAndHid(@Param("hid") String hid, @Param("uid") String uid);

    @Select("select * from ${table} where h_id = #{id}")
    Address findAddressByIdInTable(@Param("id") String id,@Param("table") String table);
}
