package com.okt.dao;

import com.okt.entity.Park;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Mapper
public interface ParkDao {

    @Select("select * from park")
    List<Park> findAllPark();

    @Select("select * from park where park_margin > 0")
    List<Park>findAllMargin();

    @Delete("delete  from park where park_id = #{id}")
    void delById(String id);

    @Insert("insert into park values(#{id},#{name},#{margin},#{margin})")
    void insertPark(@Param("id") String id,@Param("name") String name,@Param("margin") int margin);

    @Select("select park_margin from park where park_id = #{id}")
    int findMargin(String id);

    @Select("select park_margin_max from park where park_id = #{id}")
    int findMarginMax(String id);

    @Select("select * from park where park_id = #{id}")
    Park findOne(String id);

    @Update("update park set park_margin=park_margin+1 where park_id = #{id}")
    void addMargin(String id);

    @Update("update park set park_margin=park_margin-1 where park_id = #{id}")
    void reduceMargin(String id);


}
