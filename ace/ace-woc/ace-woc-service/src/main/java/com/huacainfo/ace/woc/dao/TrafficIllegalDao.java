package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.vo.TrafficIllegalQVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalVo;

public interface TrafficIllegalDao {

    TrafficIllegalVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TrafficIllegal record);

    int insertSelective(TrafficIllegal record);

    int updateByPrimaryKey(TrafficIllegal record);

    int updateByPrimaryKeySelective(TrafficIllegal record);

    
    List<TrafficIllegalVo> findList(@Param("condition") TrafficIllegalQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TrafficIllegalQVo condition);

	int isExit(TrafficIllegal record);

}