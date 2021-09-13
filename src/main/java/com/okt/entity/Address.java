package com.okt.entity;

import java.util.List;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
public class Address {
    private String h_id;
    private String name;



    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "h_id='" + h_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
