package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.vo.ResourcesVo;

public interface ResourcesMapper {
	int deleteByPrimaryKey(String resourcesId);

	int insert(Resources record);

	int insertSelective(Resources record);

	Resources selectByPrimaryKey(String resourcesId);

	int updateByPrimaryKeySelective(Resources record);

	int updateByPrimaryKey(Resources record);

	List<Map<String, Object>> selectResourcesTreeList();

	List<ResourcesVo> findList(@Param("condition") Resources condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") Resources condition);

	int isExitByName(@Param("name") String name);
	
	int isExit(Resources record);

	/**
	 * @description: 查询系统受保护的资源
	 * @param:
	 * @return: Map
	 * @author: 陈晓克
	 * @version:3.5.1
	 * @exception:
	 * @createDate: 2012-12-15
	 */
	public abstract List<Map<String, String>> loadResourceDefine();
}