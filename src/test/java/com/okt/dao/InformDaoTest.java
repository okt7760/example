package com.okt.dao;

import com.okt.GpApplication;
import com.okt.entity.Inform;
import com.okt.service.InformService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create by obeeey on 2021/3/29
 * 即使再小的帆也能远航
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
class InformDaoTest {

    @Autowired
    InformDao dao;

    @Autowired
    InformService service;

    @Test
    void findAllInform() {
        List<Inform> allInform = dao.findAllInform(1);
        for (Inform inform : allInform) {
            System.out.println(inform);
        }
    }

    @Test
    void informDetail() {
    }

    @Test
    void insertInform() {
    }

    @Test
    void delInformById() {
    }

    @Test
    void findAllInformById() {
    }

    @Test
    void findAllInsideInform() {
    }
}