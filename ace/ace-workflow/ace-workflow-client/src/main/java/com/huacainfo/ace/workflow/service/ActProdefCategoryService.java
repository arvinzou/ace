package com.huacainfo.ace.workflow.service;

import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActProdefCategory;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryVo;

public interface ActProdefCategoryService {
	
	public abstract PageResult<ActProdefCategoryVo> findActProdefCategoryList(ActProdefCategoryQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertActProdefCategory(ActProdefCategory obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateActProdefCategory(ActProdefCategory obj,UserProp userProp) throws Exception;
	public abstract SingleResult<ActProdefCategory> selectActProdefCategoryByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteActProdefCategoryByActProdefCategoryId(String id,UserProp userProp) throws Exception;
	public abstract List<Tree> selectTreeList() throws Exception ;
	
}
