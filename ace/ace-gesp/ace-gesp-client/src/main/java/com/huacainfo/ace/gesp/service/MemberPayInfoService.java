package com.huacainfo.ace.gesp.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.MemberPayInfo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;

public interface MemberPayInfoService {
	
	public abstract PageResult<MemberPayInfoVo> findMemberPayInfoList(MemberPayInfoQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertMemberPayInfo(MemberPayInfo obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateMemberPayInfo(MemberPayInfo obj,UserProp userProp) throws Exception;
	public abstract SingleResult<MemberPayInfo> selectMemberPayInfoByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteMemberPayInfoByMemberPayInfoId(String id,UserProp userProp) throws Exception;
	
	public abstract MessageResponse updateAudit(MemberPayInfo obj,UserProp userProp) throws Exception;
	
	public abstract List<Map<String,Object>> selectAnaysePayMentByMonth( String year,String deptId,String deptIdchargingItemId) throws Exception;
	
	/**
	 * 根据编号查询费用信息
	 * 
	 * @param id 编号
	 * @return MemberPayInfo
	 * @throws Exception
	 */
	public abstract MemberPayInfo selectByPrimarkey(String id) throws Exception;
	
	

	/**
	 * 判断费用没有收取时是否重复收
	 * 
	 * 已引用（会费收取时判断）
	 * @param memberCode 企业编号
	 * @param chargingItemId 收费项目编号
	 * @param status 审核状态
	 * @return MessageResponse
	 */
	public abstract MessageResponse isExitPayInfo(String memberCode, String chargingItemId, String status)throws Exception;
	
	
	
	
}
