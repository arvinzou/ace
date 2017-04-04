package com.huacainfo.ace.portal.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.SyCfg;
import com.huacainfo.ace.portal.vo.SyCfgQVo;
import com.huacainfo.ace.portal.vo.SyCfgVo;

public interface SyCfgService {	
	public abstract PageResult<SyCfgVo> findSyCfgList(SyCfgQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertSyCfg(SyCfg obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateSyCfg(SyCfg obj,UserProp userProp) throws Exception;
	public abstract SingleResult<SyCfg> selectSyCfgByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteSyCfgBySyCfgId(String id,UserProp userProp) throws Exception;	
	
	public abstract ListResult<Map<String,Object>> selectSyCfgByUser(UserProp userProp) throws Exception;
}
