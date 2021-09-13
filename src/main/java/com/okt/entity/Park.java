package com.okt.entity;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
public class Park {
    private String park_id;
    private String park_addr;
    private int park_margin;
    private int park_margin_max;


    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public String getPark_addr() {
        return park_addr;
    }

    public void setPark_addr(String park_addr) {
        this.park_addr = park_addr;
    }

    public int getPark_margin() {
        return park_margin;
    }

    public void setPark_margin(int park_margin) {
        this.park_margin = park_margin;
    }

    public int getPark_margin_max() {
        return park_margin_max;
    }

    public void setPark_margin_max(int park_margin_max) {
        this.park_margin_max = park_margin_max;
    }
}
