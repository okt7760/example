package com.okt.service.impl;

import com.okt.dao.FeedbackDao;
import com.okt.entity.Feedback;
import com.okt.service.FeedBackService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Service
public class FeedbackServiceImpl implements FeedBackService {
    @Autowired
    FeedbackDao feedbackDao;


    @Override
    public void userInsertFeedBack(String fb_id, String u_id, String fb_context) {
        Date date=new Date();
        feedbackDao.userInsertFeedback(fb_id, u_id, fb_context, date);
    }

    @Override
    public List<Feedback> selectUserAll(String username) {
        return feedbackDao.selectUserAll(username);
    }

    @Override
    public Feedback selectOne(String feedbackid) {
        return feedbackDao.selectOne(feedbackid);
    }

    @Override
    public void deleteOne(String feedbackid) {
        feedbackDao.deleteOne(feedbackid);
    }

    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbackList = feedbackDao.findAll();
        for (Feedback feedback : feedbackList) {
            feedback.setFb_date_str(DateFormat.dateToString(feedback.getFb_date(), "yyyy-MM-dd"));
        }
        return feedbackList;
    }

    @Override
    public void updateFeedbackById(String id, String reply, String processor) {
        feedbackDao.updateFeedbackById(id, reply, processor);
    }

    @Override
    public List<Feedback> findAllCheckedByEmpId(String id) {
        List<Feedback> feedbackList = feedbackDao.findAllCheckedByEmpId(id);
        for (Feedback feedback : feedbackList) {
            feedback.setFb_date_str(DateFormat.dateToString(feedback.getFb_date(), "yyyy-MM-dd"));
        }
        return feedbackList;
    }

    @Override
    public void rollbackFeedback(String id) {
        feedbackDao.rollbackFeedback(id);
    }
}
