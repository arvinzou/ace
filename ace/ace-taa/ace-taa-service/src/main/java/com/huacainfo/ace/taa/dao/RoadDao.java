package com.huacainfo.ace.taa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.taa.model.Road;
import com.huacainfo.ace.taa.vo.RoadQVo;
import com.huacainfo.ace.taa.vo.RoadVo;

public interface RoadDao {

    Road selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Road record);


    int updateByPrimaryKey(Road record);


    RoadVo selectVoByPrimaryKey(String id);

    List<RoadVo> findList(@Param("condition") RoadQVo condition,
                          @Param("start") int start,
                          @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoadQVo condition);

    int isExit(Road record);


}