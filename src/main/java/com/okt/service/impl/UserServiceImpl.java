package com.okt.service.impl;

import com.okt.dao.AddressDao;
import com.okt.dao.UserDao;
import com.okt.entity.Empolyee;
import com.okt.entity.User;
import com.okt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    AddressDao addressDao;

    @Override
    public List<User> findALL() {
        return userDao.findALL();
    }

    @Override
    public User findOne(String username) {
        User user = userDao.findOne(username);
        return user;
    }

    @Override
    public void updatePassword(String password, String username) {
        userDao.updatePassWord(password, username);
    }

    @Override
    public void deleteUser(String username) {
        userDao.deleteUserInUserList(username);
        userDao.deleteUserInFeeback(username);
        userDao.deleteUserInParkfee(username);
        addressDao.delUserAndHourse(username);
    }

    @Override
    public int addUser(User user) {
        User one = userDao.findOne(user.getR_username());
        //判断帐号是否存在
        if (one==null) {
            //身份证号相同
            if(userDao.findUserByUid(user.getR_uid())!=null){
                return 2;
            }
            //身份证不相同
            userDao.addUser(user);
            return 1;
        }
        //帐号存在
        else {
            return 3;
        }
    }

    @Override
    public List<User> findAllUnauthorizedUser() {
        return userDao.findAllUnauthorizedUser();
    }

    @Override
    public void updateUserStatus(String username, int status) {
        //判断status，若是2要在地址表删除预存信息
        if (status==1){
            userDao.updateUserStatus(username, status);
        }
        else {
            addressDao.delUserAndHourse(username);
            userDao.updateUserStatus(username, status);
        }

    }

    @Override
    public void updateUser(String username,String name, String uid, String phone,String gender) {
        userDao.updateUser(username,name, uid, phone,gender);
    }

    @Override
    public User findByUserId(String id) {
        User user = userDao.findByUserId(id);
        return user;

    }

    @Override
    public void bandCar(String id, String carName, String car,int place) {
        Date date = new Date();
        userDao.insertCarAndUser(id, carName, car,date,place);
    }

    @Override
    public String getCarName(String province, String number) {
        String provinceName = userDao.findProvinceName(province);
        String carName = provinceName + number;
        return carName;
    }

    @Override
    public List<Empolyee> findAllEmp() {
        return userDao.findAllEmp();
    }

    @Override
    public Empolyee findEmp(String id) {
        return userDao.findOneEmp(id);
    }

    @Override
    public void updateEmp(String username, String name, String uid, String phone, int gender) {
        userDao.updateEmp(username,name,uid,phone,gender);
    }

    @Override
    public void deleteEmp(String id) {
        userDao.delEmp(id);
    }

    @Override
    public void addEmp(String username, String name, String id, String phone, int gender) {
        userDao.addEmp(username,name,id,phone,gender);
    }

    @Override
    public void delUserAddress(String id, String uid) {
        userDao.deleteUserAdd(id,uid);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public boolean checkEmpId(String uid,String username) {
        Empolyee empolyee =userDao.findEmpId(uid);
        if(empolyee==null){
            return false;
        }
        else if(empolyee.getF_username().equals(username)){
            return false;
        }
        else {
            return true;
        }

    }

    @Override
    public boolean checkUserId(String uid,String username) {
        User user = userDao.findUserByUid(uid);
        if(user==null){
            return false;
        }
        else if(user.getR_username().equals(username)){
            return false;
        }
        else {
            return true;
        }

    }


}
