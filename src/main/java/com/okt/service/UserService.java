package com.okt.service;

import com.okt.entity.Empolyee;
import com.okt.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
public interface UserService {

    /**
     * 查询所有用户
     */
    List<User> findALL();

    /**
     * 查询单个用户
     */
    User findOne(String username);

    /**
     * 修改用户密码
     */
    void updatePassword( String password,String username);

    /**
     * 删除用户
     */

    void deleteUser( String username);

    /**
     * 添加用户(未审核)
     */
    int addUser(User user);


    /**
     * 查找所有未审核用户
     * @return
     */
    List<User> findAllUnauthorizedUser();

    /**
     * 修改用户审核状态
     * @param username
     * @param status
     */
    void updateUserStatus(String username,int status);

    /**
     * 修改用户姓名，电话，身份证,性别
     */
    void updateUser(String username,String name,String uid,String phone,String gender);

    User findByUserId(String id);

    void bandCar(String carId, String carName,String userId,int place);


    String getCarName(String province, String number);

    List<Empolyee> findAllEmp();

    Empolyee findEmp(String id);

    void updateEmp(String username, String name, String uid, String phone, int gender);

    void deleteEmp(String id);

    void addEmp(String username, String name, String id, String phone, int gender);

    void delUserAddress(String id, String uid);

    List<User> findUserByName(String name);

    boolean checkEmpId(String uid,String username);

    boolean checkUserId(String uid,String username);
}
