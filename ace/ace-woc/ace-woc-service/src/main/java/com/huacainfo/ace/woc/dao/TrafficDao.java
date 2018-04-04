package com.huacainfo.ace.woc.dao;

import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.woc.vo.TrafficQVo;
import com.huacainfo.ace.woc.vo.TrafficVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TrafficDao {

    TrafficVo selectByPrimaryKey(String id);

    TrafficVo selectByPrimaryKeyMsg(String id);

    int deleteByPrimaryKey(String id);

    int insert(Traffic record);

    int insertSelective(Traffic record);

    int updateByPrimaryKey(Traffic record);

    int updateByPrimaryKeySelective(Traffic record);

    
    List<TrafficVo> findList(@Param("condition") TrafficQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TrafficQVo condition);

	int isExit(Traffic record);

    List<TrafficVo> selectListByKeyWord(@Param("params") Map<String, Object> params);
}