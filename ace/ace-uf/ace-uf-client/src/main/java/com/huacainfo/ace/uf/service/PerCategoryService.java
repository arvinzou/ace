package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerCategory;
import com.huacainfo.ace.uf.vo.PerCategoryVo;
import com.huacainfo.ace.uf.vo.PerCategoryQVo;
public interface PerCategoryService {
	
	public abstract PageResult<PerCategoryVo> findPerCategoryList(PerCategoryQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerCategory(PerCategory obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerCategory(PerCategory obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerCategoryVo> selectPerCategoryByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerCategoryByPerCategoryId(String id,UserProp userProp) throws Exception;

	
}
