package com.okt.service.impl;

import com.okt.dao.ParkDao;
import com.okt.entity.Park;
import com.okt.service.ParkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by obeeey on 2021/3/22
 * 即使再小的帆也能远航
 */
@Service
public class ParkServiceImpl  implements ParkService {

    @Autowired
    ParkDao dao;

    @Override
    public List<Park> findAllMargin() {
        return dao.findAllMargin();
    }

    @Override
    public List<Park> findAll() {
        return dao.findAllPark();
    }

    @Override
    public void delPark(String id) {
        dao.delById(id);
    }

    @Override
    public void insertPark(String id, String name, String margin) {
        int margiToInt= Integer.parseInt(margin);
        dao.insertPark(id, name,margiToInt);
    }

    @Override
    public void addMargin(String id) {
        dao.addMargin(id);
    }

    @Override
    public void reduceMargin(String id) {
        dao.reduceMargin(id);
    }

    @Override
    public int findMargin(String id) {
        return dao.findMargin(id);
    }

    @Override
    public int findMarginMax(String id) {
        return dao.findMarginMax(id);
    }

    @Override
    public int parkDupicate(String id) {
        Park park = dao.findOne(id);
        if(park==null){
            return 1;
        }
        else{
            return 2;
        }

    }


}
