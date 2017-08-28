package com.huacainfo.ace.gesp.service;

import java.util.Map;

import com.huacainfo.ace.gesp.vo.MemberLevelQVo;
import com.huacainfo.ace.gesp.vo.MemberLevelVo;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.MemberLevel;

public interface MemberLevelService {
	
	public abstract PageResult<MemberLevelVo> findMemberLevelList(MemberLevelQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertMemberLevel(MemberLevel obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateMemberLevel(MemberLevel obj,UserProp userProp) throws Exception;
	public abstract SingleResult<MemberLevel> selectMemberLevelByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteMemberLevelByMemberLevelId(String id,UserProp userProp) throws Exception;
	
	
	/**
	 * 根据协会编号查询会员级别
	 * 
	 * 引用(会员级别下拉框,收费处理模块)
	 * @param memberCode
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId,String selected) throws Exception ;

}
