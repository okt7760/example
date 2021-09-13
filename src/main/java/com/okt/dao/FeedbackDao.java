package com.okt.dao;

import com.okt.entity.Feedback;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Mapper
public interface FeedbackDao {
    /**
     * 新建用户反馈信息
     */
    @Insert("insert into feedback(fb_id,r_username,fb_context,fb_date,fb_status) values(#{fb_id},#{r_username},#{fb_context},#{fb_date},0)")
    void userInsertFeedback(@Param("fb_id") String fb_id, @Param("r_username")String r_username,@Param("fb_context") String fb_context, @Param("fb_date") Date fb_date);

    /**
     * 查询当前用户所有反馈信息
     */
    @Select("select * from feedback where r_username = #{username} order by fb_date desc")
    List<Feedback> selectUserAll(String username);


    /**
     * 根据id查询反馈信息内容
     */
    @Select("select * from feedback where fb_id = #{fb_id}")
    Feedback selectOne(String fb_id);

    /**
     * 根据id删除反馈信息内容
     */
    @Delete("delete from feedback where fb_id = #{fb_id}")
    void deleteOne(String fb_id);

    /**
     * 员工查找所有未审核消息
     * @return
     */
    @Select("select * from feedback where fb_status = 0 order by fb_date desc")
    List<Feedback> findAll();

    /**
     * 回复反馈
     * @param id
     * @param reply
     * @param processor
     */
    @Update("update feedback set fb_reply=#{reply},fb_processor=#{processor},fb_status = 1 where fb_id=#{id}")
    void updateFeedbackById(@Param("id") String id,@Param("reply") String reply,@Param("processor")String processor);

    /**
     * 查询员工所审查的所有反馈
     * @param id
     * @return
     */
    @Select("select * from feedback where fb_processor = #{id} order by fb_date desc")
    List<Feedback> findAllCheckedByEmpId(String id);

    @Update("update feedback set fb_reply=null,fb_processor=null,fb_status = 0 where fb_id=#{id}")
    void rollbackFeedback(String id);

}
