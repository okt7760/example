package com.okt.entity;

/**
 * Create by obeeey on 2021/3/10
 * 即使再小的帆也能远航
 */
//地址表
public class UserAndAddress {
    private String h_id;
    private String r_username;
    private String h_id_one;
    private String h_id_two;
    private String h_id_three;
    private String h_id_four;
    private String h_name;

    public UserAndAddress(String h_id, String r_username, String h_id_one, String h_id_two, String h_id_three, String h_id_four) {
        this.h_id = h_id;
        this.r_username = r_username;
        this.h_id_one = h_id_one;
        this.h_id_two = h_id_two;
        this.h_id_three = h_id_three;
        this.h_id_four = h_id_four;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_idId(String id) {
        this.h_id = id;
    }

    public String getR_username() {
        return r_username;
    }

    public void setR_username(String r_username) {
        this.r_username = r_username;
    }

    public String getH_id_one() {
        return h_id_one;
    }

    public void setH_id_one(String h_id_one) {
        this.h_id_one = h_id_one;
    }

    public String getH_id_two() {
        return h_id_two;
    }

    public void setH_id_two(String h_id_two) {
        this.h_id_two = h_id_two;
    }

    public String getH_id_three() {
        return h_id_three;
    }

    public void setH_id_three(String h_id_three) {
        this.h_id_three = h_id_three;
    }

    public String getH_id_four() {
        return h_id_four;
    }

    public void setH_id_four(String h_id_four) {
        this.h_id_four = h_id_four;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    @Override
    public String toString() {
        return "UserAndAddress{" +
                "h_id='" + h_id + '\'' +
                ", r_username='" + r_username + '\'' +
                ", h_id_one='" + h_id_one + '\'' +
                ", h_id_two='" + h_id_two + '\'' +
                ", h_id_three='" + h_id_three + '\'' +
                ", h_id_four='" + h_id_four + '\'' +
                ", h_name='" + h_name + '\'' +
                '}';
    }
}

