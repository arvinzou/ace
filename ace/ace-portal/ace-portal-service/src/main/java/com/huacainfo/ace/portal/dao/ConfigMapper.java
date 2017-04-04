package com.huacainfo.ace.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.vo.ConfigVo;

public interface ConfigMapper {
	int deleteByPrimaryKey(String id);

	int insert(Config record);

	int insertSelective(Config record);

	Config selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Config record);

	int updateByPrimaryKey(Config record);

	List<ConfigVo> findList(@Param("condition") Config condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") Config condition);


	int isExitByName(@Param("name") String name);
	
	List<Config> selectBasicConfigListByCategory(String category);
}