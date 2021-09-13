package com.okt.dao;

import com.okt.GpApplication;
import com.okt.entity.Maintenance;
import com.okt.entity.ParkFee;
import com.okt.entity.Property;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create by obeeey on 2021/3/19
 * 即使再小的帆也能远航
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
class CostDaoTest {
    @Autowired
    CostDao dao;

    @Test
    void findOneMaintenance() {
        dao.updateUnit(3, 0, 0, 1);
    }

    @Test
    void updateStatus() {

    }
    @Test
    void findOne(){

    }
}