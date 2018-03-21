package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvTaskData;
import com.huacainfo.ace.iop.vo.EvTaskDataQVo;
import com.huacainfo.ace.iop.vo.EvTaskDataVo;

public interface EvTaskDataMapper {
    int deleteByPrimaryKey(String EvTaskDataId);

    int insert(EvTaskData record);

    int insertSelective(EvTaskData record);

    EvTaskDataVo selectByPrimaryKey(String EvTaskDataId);

    int updateByPrimaryKeySelective(EvTaskData record);

    int updateByPrimaryKey(EvTaskData record);
    
    List<EvTaskDataVo> findList(@Param("condition") EvTaskDataQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvTaskDataQVo condition);

	int isExit(EvTaskData record);
	int isExitVote(EvTaskData record);
}