package com.huacainfo.ace.woc.dao;

import java.util.List;

import com.huacainfo.ace.portal.model.Resources;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Road;
import com.huacainfo.ace.woc.vo.RoadQVo;
import com.huacainfo.ace.woc.vo.RoadVo;

public interface RoadDao {
    RoadVo selectVoByPrimaryKey(String id);

    Road selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Road record);

    int insertSelective(Road record);

    int updateByPrimaryKey(Road record);

    int updateByPrimaryKeySelective(Road record);

    List<RoadVo> findList(@Param("condition") RoadQVo condition,
                          @Param("start") int start,
                          @Param("limit") int limit,
                          @Param("orderBy") String orderBy);

    int findCount(@Param("condition") RoadQVo condition);

    int isExit(Road record);

}