package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.workflow.model.ActProdefCategory;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryVo;

public interface ActProdefCategoryDao {
    int deleteByPrimaryKey(String ActProdefCategoryId);

    int insert(ActProdefCategory record);


    ActProdefCategory selectByPrimaryKey(String ActProdefCategoryId);


    int updateByPrimaryKey(ActProdefCategory record);
    
    List<ActProdefCategoryVo> findList(@Param("condition") ActProdefCategoryQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActProdefCategoryQVo condition);

	int isExit(ActProdefCategory record);
	
	
	List<Map<String,Object>> selectTreeList();
}