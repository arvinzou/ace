package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.MessageCheck;

public interface MessageCheckMapper  {

	List<Map<String, Object>> findList(@Param("condition") Map<String, Object> condition, @Param("start") int start, @Param("limit") int limit, @Param("orderBy") String orderBy);

	int findCount(@Param("condition") Map<String, Object> condition);

	int insert(MessageCheck o);
	 

	int update(MessageCheck o);
	
	int insertSelective(MessageCheck o);
	
	int updateSelective(MessageCheck o);

	Map<String, Object> selectByPrimaryKey(@Param("id") String id);

	void deleteByPrimaryKey(@Param("id") String id);

	List<Map<String, Object>> findListTop(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy);

	
}
 