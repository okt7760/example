package com.okt.entity;

import java.util.Date;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
public class Property {
    private String prop_id;
    private String h_id;
    private Date prop_date;
    private String prop_date_str;
    private String prop_fee;
    private int prop_status;
    private String h_name;

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getProp_id() {
        return prop_id;
    }

    public void setProp_id(String prop_id) {
        this.prop_id = prop_id;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public Date getProp_date() {
        return prop_date;
    }

    public void setProp_date(Date prop_date) {
        this.prop_date = prop_date;
    }

    public String getProp_date_str() {
        return prop_date_str;
    }

    public void setProp_date_str(String prop_date_str) {
        this.prop_date_str = prop_date_str;
    }

    public String getProp_fee() {
        return prop_fee;
    }

    public void setProp_fee(String prop_fee) {
        this.prop_fee = prop_fee;
    }

    public int getProp_status() {
        return prop_status;
    }

    public void setProp_status(int prop_status) {
        this.prop_status = prop_status;
    }

    @Override
    public String toString() {
        return "Property{" +
                "prop_id='" + prop_id + '\'' +
                ", h_id='" + h_id + '\'' +
                ", prop_date='" + prop_date + '\'' +
                ", prop_fee='" + prop_fee + '\'' +
                ", prop_status='" + prop_status + '\'' +
                ", h_name='" + h_name + '\'' +
                '}';
    }
}
