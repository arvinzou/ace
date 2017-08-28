package com.huacainfo.ace.gesp.service;

import java.util.Map;

import com.huacainfo.ace.gesp.vo.RemttanceInfoQVo;
import com.huacainfo.ace.gesp.vo.RemttanceInfoVo;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.RemttanceInfo;

public interface RemttanceInfoService {
	
	public abstract PageResult<RemttanceInfoVo> findRemttanceInfoList(RemttanceInfoQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertRemttanceInfo(RemttanceInfo obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateRemttanceInfo(RemttanceInfo obj,UserProp userProp) throws Exception;
	public abstract SingleResult<RemttanceInfo> selectRemttanceInfoByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteRemttanceInfoByRemttanceInfoId(String id,UserProp userProp) throws Exception;
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId) throws Exception ;
	
}
