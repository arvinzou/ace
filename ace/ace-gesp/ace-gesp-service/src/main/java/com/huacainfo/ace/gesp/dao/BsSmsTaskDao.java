package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.BsSmsTask;
import com.huacainfo.ace.gesp.model.Sms;

public interface  BsSmsTaskDao   {

	List<Map<String, Object>> findList(@Param("condition")Map<String, Object> condition, @Param("start")int start, @Param("limit") int limit, @Param("orderBy")String orderBy);

	int findCount(@Param("condition")Map<String, Object> condition);
	
	List<Map<String, Object>> findListForSendSMS(@Param("condition")Map<String, Object> condition, @Param("start")int start, @Param("limit") int limit, @Param("orderBy")String orderBy);

	int findListForSendSMSCount(@Param("condition")Map<String, Object> condition);

	int insert(BsSmsTask o);

	int update(BsSmsTask o);

	Map<String, Object> selectByPrimaryKey(@Param("id")String id);

	int deleteByPrimaryKey(@Param("id")String id);

	List<Map<String, Object>> findListTop(@Param("condition")Map<String, Object> condition, @Param("orderBy")String orderBy);

	int insertSelective(BsSmsTask o);

	int updateSelective(BsSmsTask o);

	List<Map<String, Object>> selectUnSendSMS();

	List<Map<String, Object>> findListByMember(@Param("condition")Map<String, Object> condition, @Param("orderBy")String orderBy);
	
	
	 
}
 