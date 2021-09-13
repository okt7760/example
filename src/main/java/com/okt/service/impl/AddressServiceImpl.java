package com.okt.service.impl;

import com.okt.dao.AddressDao;

import com.okt.entity.Address;
import com.okt.entity.UserAndAddress;
import com.okt.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;


    @Override
    public String findAddressNameById(String h_id) {
        UserAndAddress address = addressDao.findAddressNameById(h_id);
        String addressName=address.getH_id_one()+address.getH_id_two()+address.getH_id_three()+address.getH_id_four();
        return addressName;
    }

    @Override
    public String findAddressNameByUsername(String username) {
        UserAndAddress address = addressDao.getAddressByUserName(username);
        String addressName=address.getH_id_one()+address.getH_id_two()+address.getH_id_three()+address.getH_id_four();
        return addressName;
    }


    @Override
    public void addAddressAndUser(UserAndAddress userAndAddress) {
        addressDao.addAddressAndUser(userAndAddress);
    }


    @Override
    public List<Address> findAllOneAddress() {
        return addressDao.findAllOneAddress();
    }

    @Override
    public List<Address> findAllTwoAddress() {
        return addressDao.findAllTwoAddress();
    }

    @Override
    public List<Address> findAllThreeAddress() {
        return addressDao.findAllThreeAddress();
    }

    @Override
    public List<Address> findAllFourAddress() {
        return addressDao.findAllFourAddress();
    }

    @Override
    public void addAddress(String tableName, String id, String name) {
        String table = "house_"+tableName;
        addressDao.addAddress(table, id, name);
    }

    @Override
    public UserAndAddress findAddressById(String id, String uid) {
        return addressDao.findAddressById(id,uid);
    }

    @Override
    public void updateAddress(String aid, String uid, String hid, String one, String two, String three, String four) {
        addressDao.updateAddress(aid,uid,hid,one,two,three,four);
    }

    @Override
    public void delAddress(String id, int mode) {
        if(mode==1){
            addressDao.delAddress("house_one",id);
        }
        else if(mode==2){
            addressDao.delAddress("house_two",id);
        }
        else if(mode==3){
            addressDao.delAddress("house_three",id);
        }
        else if(mode==4){
            addressDao.delAddress("house_four",id);
        }
        else{
            System.out.println("error");
        }
    }

    @Override
    public String findAddressOneNameById(String addOne) {
        return addressDao.findAddressOne(addOne);
    }

    @Override
    public String findAddressTwoNameById(String addTwo) {
        return addressDao.findAddressTwo(addTwo);
    }

    @Override
    public String findAddressThreeNameById(String addThree) {
        return addressDao.findAddressThree(addThree);
    }

    @Override
    public String findAddressFourNameById(String addFour) {
        return addressDao.findAddressFour(addFour);
    }

    @Override
    public UserAndAddress findUserAndAddressByUidAndHid(String hid, String uid) {
        return addressDao.findUserAndAddressByUidAndHid(hid,uid);
    }

    @Override
    public boolean findAddressByIdInTable(String id, String tableName) {
        String table = "house_"+tableName;
        Address address = addressDao.findAddressByIdInTable(id, table);
        if(address==null){
            return true;
        }
        else {
            return false;
        }
    }


}
