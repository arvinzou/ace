package com.huacainfo.ace.workflow.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActFormDef;
import com.huacainfo.ace.workflow.vo.ActFormDefQVo;
import com.huacainfo.ace.workflow.vo.ActFormDefVo;

public interface ActFormDefService {
	
	public abstract PageResult<ActFormDefVo> findActFormDefList(ActFormDefQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActFormDef(ActFormDef obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActFormDef(ActFormDef obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActFormDef> selectActFormDefByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActFormDefByActFormDefId(String id,UserProp userProp) throws Exception;
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId) throws Exception ;
	public abstract Map<String, Object> selectFormDefList(
			Map<String, String> params);
	
}
