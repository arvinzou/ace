package com.huacainfo.ace.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.LiveRpt;
import com.huacainfo.ace.live.vo.LiveRptQVo;
import com.huacainfo.ace.live.vo.LiveRptVo;

public interface LiveRptDao {
    int deleteByPrimaryKey(String id);

    int insert(LiveRpt record);


    LiveRptVo selectByPrimaryKey(String LiveSubId);


    int updateByPrimaryKey(LiveRpt record);

    int updateStatusByPrimaryKey(@Param("id") String id, @Param("status") String status);

    List<LiveRptVo> findList(@Param("condition") LiveRptQVo condition,
                             @Param("start") int start, @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveRptQVo condition);

    int isExit(LiveRpt record);

    int updateSortByPrimaryKey(@Param("id") String id, @Param("sort") int sort);

    String findNickNameByRid(@Param("rid")String rid, @Param("id")String id);
}