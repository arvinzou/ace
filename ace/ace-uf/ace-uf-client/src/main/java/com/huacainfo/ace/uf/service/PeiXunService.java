package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PeiXun;
import com.huacainfo.ace.uf.vo.PeiXunVo;
import com.huacainfo.ace.uf.vo.PeiXunQVo;
public interface PeiXunService {
	
	public abstract PageResult<PeiXunVo> findPeiXunList(PeiXunQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPeiXun(PeiXun obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePeiXun(PeiXun obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PeiXunVo> selectPeiXunByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePeiXunByPeiXunId(String id,UserProp userProp) throws Exception;

	
}
