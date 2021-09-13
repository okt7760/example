package com.okt.dao;

import com.okt.entity.Empolyee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Create by obeeey on 2021/3/15
 * 即使再小的帆也能远航
 */
@Mapper
public interface EmpolyeeDao {
    /**
     * 查找一名员工
     * @param username
     * @return
     */
    @Select("select * from firm_user where f_username = #{username}")
    Empolyee findOneEmp(String username);
    @Update("update firm_user set f_password = #{password} where f_username = #{username}")
    void updateEmpPassword(@Param("password") String newpwd, @Param("username") String username);
}
