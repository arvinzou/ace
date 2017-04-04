package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.workflow.model.ActFormFieldType;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeQVo;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeVo;

public interface ActFormFieldTypeDao {
    int deleteByPrimaryKey(String ActFormFieldTypeId);

    int insert(ActFormFieldType record);


    ActFormFieldType selectByPrimaryKey(String ActFormFieldTypeId);


    int updateByPrimaryKey(ActFormFieldType record);
    
    List<ActFormFieldTypeVo> findList(@Param("condition") ActFormFieldTypeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActFormFieldTypeQVo condition);

	int isExit(ActFormFieldType record);
	
	List<Map<String,Object>> selectListByDeptId(String deptId);
}