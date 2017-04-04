package com.huacainfo.ace.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.workflow.model.ActProdefCustom;
import com.huacainfo.ace.workflow.vo.ActProdefCustomQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCustomVo;

public interface ActProdefCustomDao {
    int deleteByPrimaryKey(String ActProdefCustomId);

    int insert(ActProdefCustom record);


    ActProdefCustom selectByPrimaryKey(String ActProdefCustomId);


    int updateByPrimaryKey(ActProdefCustom record);
    
    List<ActProdefCustomVo> findList(@Param("condition") ActProdefCustomQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") ActProdefCustomQVo condition);

	int isExit(ActProdefCustom record);
	
	List<Map<String,Object>> selectTreeList();
	
	public abstract List<Map<String, String>> selectGridList(
			@Param("params") Map<String, Object> params);
}