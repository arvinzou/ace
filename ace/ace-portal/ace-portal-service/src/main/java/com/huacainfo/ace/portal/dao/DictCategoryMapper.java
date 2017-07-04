package com.huacainfo.ace.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.DictCategory;

public interface DictCategoryMapper {

	int deleteByPrimaryKey(String categoryId);

	int insert(DictCategory record);

	int insertSelective(DictCategory record);

	DictCategory selectByPrimaryKey(String categoryId);


	int updateByPrimaryKey(DictCategory record);
	
	List<DictCategory> findList (@Param("condition")DictCategory condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	int findCount (@Param("condition")DictCategory condition);
	
	List<DictCategory> findListAll(@Param("syid")String syid);
	
	int isExitByName(@Param("name")String name);
	
}