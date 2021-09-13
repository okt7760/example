package com.okt.dao;

import com.okt.GpApplication;
import com.okt.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create by obeeey on 2021/3/15
 * 即使再小的帆也能远航
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
class UserDaoTest {

    @Autowired
    UserDao dao;

    @Test
    void findALL() {
       String money = "3.5";
       Double.parseDouble(money);
        System.out.println(money);
    }

    @Test
    void findOne() {
        User user=dao.findOne("123");
        System.out.println(user);
    }

    @Test
    void updateUser() {

    }



    @Test
    void addUser() {

    }
}