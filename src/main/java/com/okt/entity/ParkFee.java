package com.okt.entity;

import java.util.Date;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
public class ParkFee {
    private String car_id;
    private String car_name;
    private String r_username;
    private String expire_str;
    private Date expire;
    private int balance;
    private int status;
    //0：室外 1：室内
    private int place;
    private String placeStr;

    public String getExpire_str() {
        return expire_str;
    }

    public void setExpire_str(String expire_str) {
        this.expire_str = expire_str;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getR_username() {
        return r_username;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getPlaceStr() {
        return placeStr;
    }

    public void setPlaceStr(String placeStr) {
        this.placeStr = placeStr;
    }
}
