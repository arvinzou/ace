package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.Sms;

public interface  SmsMapper   {

	List<Map<String, Object>> findList(@Param("condition") Map<String, Object> condition, @Param("start") int start, @Param("limit") int limit, @Param("orderBy") String orderBy);

	int findCount(@Param("condition") Map<String, Object> condition);

	int insert(Sms o);

	int update(Sms o);

	Map<String, Object> selectByPrimaryKey(@Param("id") String id);

	int deleteByPrimaryKey(@Param("id") String id);

	List<Map<String, Object>> findListTop(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy);

	int insertSelective(Sms o);

	int updateSelective(Sms o);

	List<Map<String, Object>> selectUnSendSMS();

	int insertSelectiveByBussId(@Param("oldTaskId") String oldTaskId, @Param("newTaskId") String newTaskId);
	 
}
 