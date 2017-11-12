package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.TongXin;
import com.huacainfo.ace.uf.vo.TongXinVo;
import com.huacainfo.ace.uf.vo.TongXinQVo;
public interface TongXinService {
	
	public abstract PageResult<TongXinVo> findTongXinList(TongXinQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertTongXin(TongXin obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateTongXin(TongXin obj,UserProp userProp) throws Exception;
	public abstract SingleResult<TongXinVo> selectTongXinByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteTongXinByTongXinId(String id,UserProp userProp) throws Exception;

	
}
