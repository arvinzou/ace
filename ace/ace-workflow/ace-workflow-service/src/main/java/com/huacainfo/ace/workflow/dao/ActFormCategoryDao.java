package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.workflow.model.ActFormCategory;
import com.huacainfo.ace.workflow.vo.ActFormCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActFormCategoryVo;

public interface ActFormCategoryDao {
    int deleteByPrimaryKey(String ActFormCategoryId);

    int insert(ActFormCategory record);


    ActFormCategory selectByPrimaryKey(String ActFormCategoryId);


    int updateByPrimaryKey(ActFormCategory record);
    
    List<ActFormCategoryVo> findList(@Param("condition") ActFormCategoryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActFormCategoryQVo condition);

	int isExit(ActFormCategory record);
	
	
	List<Map<String,Object>> selectTreeList();
}