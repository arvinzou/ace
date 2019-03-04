package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.vo.ActivityDetailQVo;
import com.huacainfo.ace.society.vo.ActivityDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDetailDao {

    ActivityDetail selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ActivityDetail record);

    int insertSelective(ActivityDetail record);

    int updateByPrimaryKey(ActivityDetail record);

    int updateByPrimaryKeySelective(ActivityDetail record);

    ActivityDetailVo selectVoByPrimaryKey(String id);


    ActivityDetailVo selectPersonaldetails(@Param("activityId")String activityId,@Param("unionId")String unionId);

    List<ActivityDetailVo> findList(@Param("condition") ActivityDetail condition, @Param("start") int start, @Param("limit") int limit, @Param("orderBy") String orderBy);

    int findRealNum(@Param("condition") ActivityDetail condition, @Param("start") int start, @Param("limit") int limit);
    int findCount(@Param("condition") ActivityDetailQVo condition);

    int isExit(ActivityDetail record);

}