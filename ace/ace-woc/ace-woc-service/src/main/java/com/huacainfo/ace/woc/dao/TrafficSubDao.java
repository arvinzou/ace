package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.TrafficSub;
import com.huacainfo.ace.woc.vo.TrafficSubQVo;
import com.huacainfo.ace.woc.vo.TrafficSubVo;

public interface TrafficSubDao {

    TrafficSubVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TrafficSub record);

    int insertSelective(TrafficSub record);

    int updateByPrimaryKey(TrafficSub record);

    int updateByPrimaryKeySelective(TrafficSub record);


    
    List<TrafficSubVo> findList(@Param("condition") TrafficSubQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

    List<TrafficSubVo> findListByTrafficId(String id);

	int findCount(@Param("condition") TrafficSubQVo condition);

	int isExit(TrafficSub record);

}