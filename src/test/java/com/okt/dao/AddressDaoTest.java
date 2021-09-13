package com.okt.dao;

import com.okt.GpApplication;
import com.okt.entity.UserAndAddress;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create by obeeey on 2021/3/17
 * 即使再小的帆也能远航
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
class AddressDaoTest {
        @Autowired
        AddressDao dao;

    @Test
    void findAddressOne() {
        dao.delAddress("house_one", "2");

    }
}