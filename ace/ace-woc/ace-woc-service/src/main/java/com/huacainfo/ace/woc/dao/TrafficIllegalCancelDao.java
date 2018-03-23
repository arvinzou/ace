package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.TrafficIllegalCancel;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelQVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelVo;

public interface TrafficIllegalCancelDao {

    TrafficIllegalCancelVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TrafficIllegalCancel record);

    int insertSelective(TrafficIllegalCancel record);

    int updateByPrimaryKey(TrafficIllegalCancel record);

    int updateByPrimaryKeySelective(TrafficIllegalCancel record);

    
    List<TrafficIllegalCancelVo> findList(@Param("condition") TrafficIllegalCancelQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TrafficIllegalCancelQVo condition);

	int isExit(TrafficIllegalCancel record);

}