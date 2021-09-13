package com.okt.service;

import com.okt.entity.Address;
import com.okt.entity.UserAndAddress;

import java.util.List;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
public interface AddressService {

    String findAddressNameById(String h_id);

    String findAddressNameByUsername(String username);

    void addAddressAndUser(UserAndAddress userAndAddress);

    /**
     * 寻找所有一号地址位
     * @return
     */
    List<Address> findAllOneAddress();

    /**
     * 寻找所有二号地址位
     * @return
     */
    List<Address> findAllTwoAddress();

    /**
     * 寻找所有三号地址位
     * @return
     */
    List<Address> findAllThreeAddress();

    /**
     * 寻找所有四号地址位
     * @return
     */
    List<Address> findAllFourAddress();

    /**
     * 添加地址
     */
    void addAddress(String tableName,String id,String name);


    UserAndAddress findAddressById(String id, String uid);

    void updateAddress(String aid, String uid, String hid, String one, String two, String three, String four);

    void delAddress(String id, int mode);

    String findAddressOneNameById(String addOne);

    String findAddressTwoNameById(String addTwo);

    String findAddressThreeNameById(String addThree);

    String findAddressFourNameById(String addFour);

    UserAndAddress findUserAndAddressByUidAndHid(String hid, String uid);

    boolean findAddressByIdInTable(String id, String tableName);
}
