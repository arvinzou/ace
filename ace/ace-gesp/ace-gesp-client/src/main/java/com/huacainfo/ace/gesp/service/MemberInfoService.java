package com.huacainfo.ace.gesp.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.vo.MemberInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberInfoVo;

public interface MemberInfoService {
	public abstract PageResult<MemberInfoVo> findMemberInfoList(MemberInfoQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertMemberInfo(MemberInfo obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateAudit(MemberInfo obj,UserProp userProp) throws Exception;
	public abstract SingleResult<MemberInfo> selectMemberInfoByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteMemberInfoByMemberInfoId(String id,UserProp userProp) throws Exception;
	
	public abstract SingleResult<Department> loadMemberBaseInfo(String id, String parentDepartmentId) throws Exception;
	
	public MessageResponse updateByPrimaryKeySelective(Department o, UserProp userProp) throws Exception;
	
	public abstract MessageResponse insertMemberInfoByAdmin(MemberInfo obj,UserProp userProp) throws Exception;
	
	public MessageResponse updateMemberBaseByPrimaryKey(MemberInfo o, UserProp userProp) throws Exception;
	
	/**
	 * 会员退会
	 * @param obj 
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public abstract MessageResponse updateState(MemberInfo obj, UserProp curUserProp);
	

}
