package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.OrganizationSub;
import com.huacainfo.ace.uf.vo.OrganizationSubVo;
import com.huacainfo.ace.uf.vo.OrganizationSubQVo;
public interface OrganizationSubService {
	
	public abstract PageResult<OrganizationSubVo> findOrganizationSubList(OrganizationSubQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertOrganizationSub(OrganizationSub obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateOrganizationSub(OrganizationSub obj,UserProp userProp) throws Exception;
	public abstract SingleResult<OrganizationSubVo> selectOrganizationSubByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteOrganizationSubByOrganizationSubId(String id,UserProp userProp) throws Exception;

	
}
