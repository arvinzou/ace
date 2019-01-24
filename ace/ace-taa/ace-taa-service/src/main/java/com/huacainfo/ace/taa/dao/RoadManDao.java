package com.huacainfo.ace.taa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.taa.model.RoadMan;
import com.huacainfo.ace.taa.vo.RoadManQVo;
import com.huacainfo.ace.taa.vo.RoadManVo;

public interface RoadManDao {

    RoadMan selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(RoadMan record);


    int updateByPrimaryKey(RoadMan record);


    RoadManVo selectVoByPrimaryKey(String id);

    List<RoadManVo> findList(@Param("condition") RoadManQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoadManQVo condition);

    int isExit(RoadMan record);

    int updateStatus(RoadMan record);


    List<Map<String, Object>> getList(@Param("p") Map<String, Object> p);

    RoadMan selectByName(@Param("name") String name);

    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    List<Map<String, Object>> findRoster(@Param("roadManName") String roadManName);
}