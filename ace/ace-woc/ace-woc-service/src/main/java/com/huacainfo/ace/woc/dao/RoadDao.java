package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Road;
import com.huacainfo.ace.woc.vo.RoadQVo;
import com.huacainfo.ace.woc.vo.RoadVo;

public interface RoadDao {
    int deleteByPrimaryKey(String RoadId);

    int insert(Road record);


    RoadVo selectByPrimaryKey(String RoadId);


    int updateByPrimaryKey(Road record);
    
    List<RoadVo> findList(@Param("condition") RoadQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") RoadQVo condition);

	int isExit(Road record);

}