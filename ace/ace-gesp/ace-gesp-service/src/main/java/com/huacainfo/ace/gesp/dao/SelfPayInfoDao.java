package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import org.apache.ibatis.annotations.Param;

public interface SelfPayInfoDao {
	
	List<MemberPayInfoVo> findList(
			@Param("condition") MemberPayInfoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MemberPayInfoQVo condition);
	
	List<Map<String, Object>> selectAnaysePayMentByMonth(
			@Param("year") String year, @Param("deptId") String deptId,
			@Param("chargingItemId") String deptIdchargingItemId);

}