package com.okt.dao;

import com.okt.entity.Inform;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Mapper
public interface InformDao {

    /**
     * 查找通知
     * @return
     */
    @Select("select * from inform where inf_role = #{inf_role} order by desc_date desc")
    List<Inform> findAllInform(@Param("inf_role") int inf_role);



    /**
     * 根据ID查询通知
     * @return
     */
    @Select("select * from inform where inf_id = #{inf_id}")
    Inform informDetail(String inf_id);


    @Insert("insert into inform values(#{id},#{title},#{context},#{writer},#{inf_role},#{desc_date})")
    void insertInform(@Param("id") String id, @Param("title")String title, @Param("context")String context, @Param("writer")String writer,@Param("desc_date") Date desc_date,@Param("inf_role")int role);

    @Delete("delete from inform where inf_id = #{id}")
    void delInformById(String id);

    @Select("select * from inform where inf_writer = #{id} order by desc_date desc")
    List<Inform> findAllInformById(String id);

    @Select("select * from inform  order by desc_date desc")
    List<Inform> findAllInformNoneRole();
}
