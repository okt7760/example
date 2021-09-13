package com.okt.dao;

import com.okt.entity.Address;
import com.okt.entity.Empolyee;
import com.okt.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/15
 * 即使再小的帆也能远航
 */
@Mapper
public interface UserDao {

    /**
     * 查询所有已审核用户
     */
    @Select("select * from res_user where r_status = 1")
    List<User> findALL();

    /**
     * 查询单个用户
     */
    @Results(id = "UserMap", value = {
            @Result(column = "r_username", property = "r_username", id = true),
            @Result(column = "r_password", property = "r_password"),
            @Result(column = "r_name", property = "r_name"),
            @Result(column = "r_uid", property = "r_uid"),
            @Result(column = "r_gender", property = "r_gender"),
            @Result(column = "r_phone", property = "r_phone"),
            @Result(column = "r_status", property = "r_status"),
            @Result(column = "r_username", property = "address"
                    , many = @Many(select = "com.okt.dao.AddressDao.getAddressByUserName",
                    fetchType = FetchType.LAZY))
    })
    @Select("select * from res_user where r_username = #{r_name}")
    User findOne(@Param("r_name") String username);

    /**
     * 修改用户密码
     */
    @Update("update res_user set r_password = #{password} where r_username = #{username}")
    int updatePassWord(@Param("password") String password, @Param("username") String username);

    /**
     * 删除个人信息（用户表）
     */
    @Delete("delete from res_user where r_username = #{username}")
    void deleteUserInUserList(String username);

    /**
     * 删除个人信息（停车费表）
     */
    @Delete("delete from parkfee where r_username = #{username}")
    void deleteUserInParkfee(String username);

    /**
     * 删除个人信息（反馈表）
     */
    @Delete("delete from feedback where r_username = #{username}")
    void deleteUserInFeeback(String username);

    @Delete("delete from house_user where h_id=#{id} and r_username=#{uid}")
    void deleteUserAdd(String id, String uid);

    /**
     * 添加用户(未审核)
     */
    @Insert("insert into res_user values(#{r_username},#{r_password},#{r_uid},#{r_gender},#{r_name},#{r_phone},0)")
    void addUser(User user);

    /**
     * 查找所有未审核用户
     * @return
     */
    @Select("select * from res_user where r_status = 0")
    List<User> findAllUnauthorizedUser();

    /**
     * 用户审核状态更新
     * @param username
     * @param status
     */
    @Update("update res_user set r_status = #{status} where r_username = #{username}")
    void updateUserStatus(@Param("username") String username,@Param("status") int status);

    @Update("update res_user set r_name=#{name},r_uid=#{uid},r_phone=#{phone},r_gender=#{gender} where r_username = #{username}")
    void updateUser(@Param("username")String username,@Param("name") String name,@Param("uid")String uid,@Param("phone")String phone,@Param("gender")String gender);

    @Select("select * from res_user where r_uid = #{id}")
    User findByUserId(String id);

    @Insert("insert into parkfee(car_id,car_name,r_username,expire,status,place) values(#{id},#{name},#{userId},#{date},0,#{place})")
    void insertCarAndUser(@Param("id") String carId,@Param("name") String carName,@Param("userId")String userId,@Param("date") Date date,@Param("place")int place);

    @Select("select province from car where id=#{province} ")
    String findProvinceName(String province);

    /**
     * 查询所有员工
     * @return
     */
    @Select("select * from firm_user where f_username != 'admin' ")
    List<Empolyee> findAllEmp();

    /**
     * 查询一个员工
     */
    @Select("select * from firm_user where f_username = #{id}")
    Empolyee findOneEmp(String id);

    /**
     * 员工资料更新
     * @param username
     * @param name
     * @param uid
     * @param phone
     * @param gender
     */
    @Update("update firm_user set f_name = #{name},f_id=#{uid},f_phone=#{phone},f_gender=#{gender} where f_username = #{username}")
    void updateEmp(@Param("username") String username, @Param("name")String name, @Param("uid")String uid,@Param("phone") String phone, @Param("gender")int gender);

    @Delete("delete  from firm_user where f_username = #{id}")
    void delEmp(String id);

    @Insert("insert into firm_user values(#{username},123456,#{id},#{gender},#{name},#{phone},1)")
    void addEmp(@Param("username") String username,@Param("name")  String name, @Param("id") String id, @Param("phone") String phone,@Param("gender")  int gender);

    @Select("select * from res_user where r_name like '${name}%%' and r_status = 1")
    List<User> findUserByName(String name);

    @Select("select * from res_user where r_uid = #{uid}")
    User findUserByUid(String uid);

    @Select("select * from firm_user where f_id = #{uid}")
    Empolyee findEmpId(String uid);
}