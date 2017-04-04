package com.huacainfo.ace.workflow.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActProdefCustom;
import com.huacainfo.ace.workflow.vo.ActProdefCustomQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCustomVo;

public interface ActProdefCustomService {
	
	public abstract PageResult<ActProdefCustomVo> findActProdefCustomList(ActProdefCustomQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActProdefCustom(ActProdefCustom obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActProdefCustom(ActProdefCustom obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActProdefCustom> selectActProdefCustomByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActProdefCustomByActProdefCustomId(String id,UserProp userProp) throws Exception;
	public abstract List<Tree> selectTreeList() throws Exception ;
	public abstract Map<String, Object> selectGridList(Map<String, Object> params)throws Exception ;
	
}
