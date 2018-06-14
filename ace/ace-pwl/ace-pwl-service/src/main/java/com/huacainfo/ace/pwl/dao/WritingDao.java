package com.huacainfo.ace.pwl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.pwl.model.Writing;
import com.huacainfo.ace.pwl.vo.WritingQVo;
import com.huacainfo.ace.pwl.vo.WritingVo;

public interface WritingDao {
    int deleteByPrimaryKey(String WritingId);

    int insert(Writing record);


    Writing selectByPrimaryKey(String WritingId);


    int updateByPrimaryKey(Writing record);

    List<WritingVo> findList(@Param("condition") WritingQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") WritingQVo condition);

    int isExit(Writing record);

    int updateReading(@Param("condition") WritingQVo condition);

}