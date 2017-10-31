package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.PerHobby;
import com.huacainfo.ace.uf.vo.PerHobbyVo;
import com.huacainfo.ace.uf.vo.PerHobbyQVo;
public interface PerHobbyService {
	
	public abstract PageResult<PerHobbyVo> findPerHobbyList(PerHobbyQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPerHobby(PerHobby obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePerHobby(PerHobby obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PerHobbyVo> selectPerHobbyByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePerHobbyByPerHobbyId(String id,UserProp userProp) throws Exception;

	
}
