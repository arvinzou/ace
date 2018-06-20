package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.vo.DictVo;

public interface DictMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Dict record);

	int insertSelective(Dict record);

	Dict selectByPrimaryKey(Integer id);


	int updateByPrimaryKey(Dict record);
	List<DictVo> findList (@Param("condition")Dict condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
	
	int findCount (@Param("condition")Dict condition);
	
	List<Dict> findListByCategoryId(@Param("categoryId")String categoryId);
	
	int isExitByNameAndCategoryId(@Param("name")String name,@Param("categoryId")String categoryId);

    List<Map<String,Object>> selectDictTreeList(@Param("pid")String pid,@Param("syid")String syid);

	int isExit(Dict record);
	List<Map<String,String>> selectAreaCode();

	List<Map<String,Object>> selectDictAllTreeByCategoryId(String id);
}