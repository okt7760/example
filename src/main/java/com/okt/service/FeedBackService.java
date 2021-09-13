package com.okt.service;

import com.okt.entity.Feedback;


import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
public interface FeedBackService {
    /**
     * 新建反馈信息
     */
    void userInsertFeedBack(String fb_id, String u_id, String fb_context);

    /**
     * 查询当前用户所有反馈信息
     */
    List<Feedback> selectUserAll(String username);


    /**
     * 根据id查询反馈信息内容
     */
    Feedback selectOne(String feedbackid);

    /**
     * 根据id删除反馈信息内容
     */
    void deleteOne(String feedbackid);

    /**
     * 查找所有未审核有意见反馈
     * @return
     */
    List<Feedback> findAll();

    void updateFeedbackById( String id,  String reply,String processor);

    List<Feedback> findAllCheckedByEmpId(String id);

    void rollbackFeedback(String id);

}
