package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.FuPin;
import com.huacainfo.ace.uf.vo.FuPinVo;
import com.huacainfo.ace.uf.vo.FuPinQVo;
public interface FuPinService {
	
	public abstract PageResult<FuPinVo> findFuPinList(FuPinQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertFuPin(FuPin obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateFuPin(FuPin obj,UserProp userProp) throws Exception;
	public abstract SingleResult<FuPinVo> selectFuPinByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteFuPinByFuPinId(String id,UserProp userProp) throws Exception;

	
}
