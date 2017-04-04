package com.huacainfo.ace.workflow.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActFormFieldType;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeQVo;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeVo;

public interface ActFormFieldTypeService {
	
	public abstract PageResult<ActFormFieldTypeVo> findActFormFieldTypeList(ActFormFieldTypeQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActFormFieldType(ActFormFieldType obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActFormFieldType(ActFormFieldType obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActFormFieldType> selectActFormFieldTypeByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActFormFieldTypeByActFormFieldTypeId(String id,UserProp userProp) throws Exception;
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId) throws Exception ;
	
}
