package com.huacainfo.ace.workflow.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActFormCategory;
import com.huacainfo.ace.workflow.vo.ActFormCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActFormCategoryVo;

public interface ActFormCategoryService {
	
	public abstract PageResult<ActFormCategoryVo> findActFormCategoryList(ActFormCategoryQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActFormCategory(ActFormCategory obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActFormCategory(ActFormCategory obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActFormCategory> selectActFormCategoryByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActFormCategoryByActFormCategoryId(String id,UserProp userProp) throws Exception;
	public abstract List<Tree> selectTreeList() throws Exception ;
	
}
