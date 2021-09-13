package com.okt.dao;

import com.okt.GpApplication;
import com.okt.entity.Feedback;
import com.okt.util.DateFormat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
class FeedbackDaoTest {

    @Autowired
    FeedbackDao dao;

    @Test
    void userInsertFeedback() {

    }

    @Test
    void selectUserAll() {
        List<Feedback> feedbacks = dao.selectUserAll("123");
        for (Feedback feedback : feedbacks) {
            System.out.println(feedback);
        }
    }

    @Test
    void selectOne() {
        Feedback feedback = dao.selectOne("123");
        System.out.println(feedback);
    }

    @Test
    void deleteOne() {
        dao.deleteOne("123");
    }
}