package com.huacainfo.ace.live.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;

public interface LiveDao {
    int deleteByPrimaryKey(String LiveId);

    int insert(Live record);


    LiveVo selectByPrimaryKey(String LiveId);


    int updateByPrimaryKey(Live record);

    List<LiveVo> findList(@Param("condition") LiveQVo condition,
                          @Param("start") int start, @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") LiveQVo condition);

    int isExit(Live record);

    List<Map<String, Object>> getLiveList(Map<String, Object> p);

    Map<String, Object> getLive(String id);

    int updateNopPop(@Param("id") String id, @Param("nop") long nop, @Param("pop") long pop);

    List<Map<String, Object>> getLiveSubList(Map<String, Object> p);

    List<Map<String, Object>> getLiveMsgList(Map<String, Object> p);

    Map<String, Object> getTotalNumAndOrgInfo(@Param("deptId") String deptId);

    Map<String, Object> getShareContent(@Param("deptId") String deptId);



}