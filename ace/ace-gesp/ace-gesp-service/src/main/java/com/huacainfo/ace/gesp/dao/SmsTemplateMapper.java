package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.Sms;
import com.huacainfo.ace.gesp.model.SmsTemplate;

public interface  SmsTemplateMapper  {

	List<Map<String, Object>> findList(@Param("condition") Map<String, Object> condition, @Param("start") int start, @Param("limit") int limit, @Param("orderBy") String orderBy);

	int findCount(@Param("condition") Map<String, Object> condition);

	int insert(SmsTemplate o);

	int update(SmsTemplate o);

	Map<String, Object> selectByPrimaryKey(@Param("id") String id);

	int deleteByPrimaryKey(@Param("id") String id);

	List<Map<String, Object>> findListTop(@Param("condition") Map<String, Object> condition, @Param("orderBy") String orderBy);

	int insertSelective(SmsTemplate o);

	int updateSelective(SmsTemplate o);	 
}
 