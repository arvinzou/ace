package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.workflow.model.ActFormDef;
import com.huacainfo.ace.workflow.vo.ActFormDefQVo;
import com.huacainfo.ace.workflow.vo.ActFormDefVo;

public interface ActFormDefDao {
    int deleteByPrimaryKey(String ActFormDefId);

    int insert(ActFormDef record);


    ActFormDef selectByPrimaryKey(String ActFormDefId);


    int updateByPrimaryKey(ActFormDef record);
    
    List<ActFormDefVo> findList(@Param("condition") ActFormDefQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActFormDefQVo condition);

	int isExit(ActFormDef record);
	
	List<Map<String,Object>> selectListByDeptId(String deptId);
	List<Map<String,Object>> selectFormDefList(@Param("params") Map<String, String> params);
	
}