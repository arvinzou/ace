package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerHp;
import com.huacainfo.ace.uf.vo.PerHpVo;
import com.huacainfo.ace.uf.vo.PerHpQVo;
public interface PerHpService {
	
	public abstract PageResult<PerHpVo> findPerHpList(PerHpQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerHp(PerHp obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerHp(PerHp obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerHpVo> selectPerHpByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerHpByPerHpId(String id,UserProp userProp) throws Exception;

	
}
