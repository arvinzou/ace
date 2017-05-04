package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.NormCfg;
import com.huacainfo.ace.operana.vo.NormCfgVo;
import com.huacainfo.ace.operana.vo.NormCfgQVo;
public interface NormCfgService {
	
	public abstract PageResult<NormCfgVo> findNormCfgList(NormCfgQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertNormCfg(NormCfg obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateNormCfg(NormCfg obj,UserProp userProp) throws Exception;
	public abstract SingleResult<NormCfg> selectNormCfgByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteNormCfgByNormCfgId(String id,UserProp userProp) throws Exception;

	
}
