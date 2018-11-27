package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.FeedBack;
import com.huacainfo.ace.society.vo.FeedBackQVo;
import com.huacainfo.ace.society.vo.FeedBackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackDao {

    FeedBack selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FeedBack record);

    int updateByPrimaryKey(FeedBack record);

    FeedBackVo selectVoByPrimaryKey(String id);

    List<FeedBackVo> findList(@Param("condition") FeedBackQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FeedBackQVo condition);

    int isExist(FeedBack record);

}