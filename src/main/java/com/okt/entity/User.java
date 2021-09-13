package com.okt.entity;

import java.util.List;

/**
 * Create by obeeey on 2021/2/1
 * 即使再小的帆也能远航
 */
//业主
public class User {
    private String r_username; //必填 帐号
    private String r_name;     //必填 名字
    private String r_password; //必填 密码
    private String r_uid;      //必填 身份证
    private int r_gender;      //必填  性别
    private String r_phone;    //必填 稻米节爱
    private List<UserAndAddress> address; //住址
    private int r_status;//审核状态
    private String h_name;

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getR_username() {
        return r_username;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_password() {
        return r_password;
    }

    public void setR_password(String r_password) {
        this.r_password = r_password;
    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) {
        this.r_uid = r_uid;
    }

    public int getR_gender() {
        return r_gender;
    }

    public void setR_gender(int r_gender) {
        this.r_gender = r_gender;
    }

    public String getR_phone() {
        return r_phone;
    }

    public void setR_phone(String r_phone) {
        this.r_phone = r_phone;
    }

    public List<UserAndAddress> getAddress() {
        return address;
    }

    public void setAddress(List<UserAndAddress> address) {
        this.address = address;
    }

    public int getR_status() {
        return r_status;
    }

    public void setR_status(int r_status) {
        this.r_status = r_status;
    }

    @Override
    public String toString() {
        return "User{" +
                "r_username='" + r_username + '\'' +
                ", r_name='" + r_name + '\'' +
                ", r_password='" + r_password + '\'' +
                ", r_uid='" + r_uid + '\'' +
                ", r_gender='" + r_gender + '\'' +
                ", r_phone='" + r_phone + '\'' +
                ", address=" + address +
                ", r_status=" + r_status +
                '}';
    }
}
