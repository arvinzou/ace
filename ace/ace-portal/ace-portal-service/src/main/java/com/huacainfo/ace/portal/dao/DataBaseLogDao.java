package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Logs;

public interface DataBaseLogDao {
	abstract public int  insert(@Param("logs")Logs logs);
	
	int findCount (@Param("condition")Map<String,Object> condition);
	
	List<Map<String,Object>> findList (@Param("condition")Map<String,Object> condition,@Param("start")int start,@Param("limit") int limit,@Param("orderBy")String orderBy);
}
