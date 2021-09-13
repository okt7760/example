package com.okt.service;

import com.okt.entity.Park;

import java.util.List;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
public interface ParkService {

    List<Park> findAllMargin();

    List<Park> findAll();

    void delPark(String id);

    void insertPark(String id, String name, String margin);

    void addMargin(String id);

    void reduceMargin(String id);

    int findMargin(String id);

    int findMarginMax(String id);

    int parkDupicate(String id);
}
