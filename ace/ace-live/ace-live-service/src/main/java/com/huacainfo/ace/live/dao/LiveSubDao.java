package com.huacainfo.ace.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.LiveSub;
import com.huacainfo.ace.live.vo.LiveSubQVo;
import com.huacainfo.ace.live.vo.LiveSubVo;

public interface LiveSubDao {
    int deleteByPrimaryKey(String LiveSubId);

    int insert(LiveSub record);


    LiveSubVo selectByPrimaryKey(String LiveSubId);


    int updateByPrimaryKey(@Param("id") String id, @Param("status") String status);

    List<LiveSubVo> findList(@Param("condition") LiveSubQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveSubQVo condition);

    int isExit(LiveSub record);

    int updateSortByPrimaryKey(@Param("id") String id, @Param("sort") int sort);

}