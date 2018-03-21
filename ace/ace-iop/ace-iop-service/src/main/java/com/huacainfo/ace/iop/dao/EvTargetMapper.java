package com.huacainfo.ace.iop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvTarget;
import com.huacainfo.ace.iop.vo.EvTargetQVo;
import com.huacainfo.ace.iop.vo.EvTargetVo;

public interface EvTargetMapper {
    int deleteByPrimaryKey(String EvTargetId);

    int insert(EvTarget record);

    int insertSelective(EvTarget record);

    EvTargetVo selectByPrimaryKey(String EvTargetId);

    int updateByPrimaryKeySelective(EvTarget record);

    int updateByPrimaryKey(EvTarget record);
    
    List<EvTargetVo> findList(@Param("condition") EvTargetQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvTargetQVo condition);

	int isExit(EvTarget record);
}