package com.okt.entity;

import java.util.Date;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
public class Maintenance {
    private String id;
    private String h_id;
    private String water_v;
    private String water_fee;
    private String electric_v;
    private String electric_fee;
    private String gas_v;
    private String gas_fee;
    private Date date;
    private String date_str;
    private int status;
    private String h_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getWater_v() {
        return water_v;
    }

    public void setWater_v(String water_v) {
        this.water_v = water_v;
    }

    public String getWater_fee() {
        return water_fee;
    }

    public void setWater_fee(String water_fee) {
        this.water_fee = water_fee;
    }

    public String getElectric_v() {
        return electric_v;
    }

    public void setElectric_v(String electric_v) {
        this.electric_v = electric_v;
    }

    public String getElectric_fee() {
        return electric_fee;
    }

    public void setElectric_fee(String electric_fee) {
        this.electric_fee = electric_fee;
    }

    public String getGas_v() {
        return gas_v;
    }

    public void setGas_v(String gas_v) {
        this.gas_v = gas_v;
    }

    public String getGas_fee() {
        return gas_fee;
    }

    public void setGas_fee(String gas_fee) {
        this.gas_fee = gas_fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate_str() {
        return date_str;
    }

    public void setDate_str(String date_str) {
        this.date_str = date_str;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id='" + id + '\'' +
                ", h_id='" + h_id + '\'' +
                ", water_v='" + water_v + '\'' +
                ", water_fee='" + water_fee + '\'' +
                ", electric_v='" + electric_v + '\'' +
                ", electric_fee='" + electric_fee + '\'' +
                ", gas_v='" + gas_v + '\'' +
                ", gas_fee='" + gas_fee + '\'' +
                ", date=" + date +
                ", date_str='" + date_str + '\'' +
                ", status=" + status +
                ", h_name='" + h_name + '\'' +
                '}';
    }
}
