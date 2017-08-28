package com.huacainfo.ace.gesp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.MemberPayInfo;

public interface MemberPayInfoDao {

	int deleteByPrimaryKey(@Param("id")String id,@Param("endDate")Date endDate,@Param("memberLevel")String memberLevel);

	int insert(MemberPayInfo record);

	MemberPayInfo selectByPrimaryKey(String id);

	int updateByPrimaryKey(MemberPayInfo record);

	List<MemberPayInfoVo> findList(
			@Param("condition") MemberPayInfoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MemberPayInfoQVo condition);

	int updateMemberPayInfo(MemberPayInfo record);

	int updateAudit(MemberPayInfo record);

	List<Map<String, Object>> selectAnaysePayMentByMonth(
			@Param("year") String year, @Param("deptId") String deptId,
			@Param("chargingItemId") String deptIdchargingItemId);


	/**
	 * 判断费用没有收取时是否重复收
	 * 
	 * 已引用（会费收取时判断）
	 * @param memberCode 企业编号
	 * @param chargingItemId 收费项目编号
	 * @param status 审核状态
	 * @return MessageResponse
	 */
	int isExitPayInfo(@Param("memberCode") String memberCode, @Param("chargingItemId") String chargingItemId, @Param("status") String status);

	
}