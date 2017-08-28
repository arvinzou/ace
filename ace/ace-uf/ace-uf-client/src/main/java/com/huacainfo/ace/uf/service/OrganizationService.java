package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.Organization;
import com.huacainfo.ace.uf.vo.OrganizationVo;
import com.huacainfo.ace.uf.vo.OrganizationQVo;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
	
	public abstract PageResult<OrganizationVo> findOrganizationList(OrganizationQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertOrganization(Organization obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateOrganization(Organization obj,UserProp userProp) throws Exception;
	public abstract SingleResult<OrganizationVo> selectOrganizationByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteOrganizationByOrganizationId(String id,UserProp userProp) throws Exception;

	public abstract List<Map<String,Object>> selectOrganizationList(String q,WxUser user) throws Exception;

	public abstract Map<String,Object> selectOrganization(String id,WxUser user) throws Exception;
	public abstract List<Map<String,Object>> selectOrganizationListMap(WxUser user,String longitude,String latitude) throws Exception;

	public abstract List<Map<String,Object>> selectAreaCodeList(String areaCode,WxUser user) throws Exception;
	public  List<Map<String,Object>> selectOrganizationCategoryList(WxUser user) throws Exception;
	public  List<Map<String,Object>> selectOrganizationByCategory(String category,WxUser user) throws Exception;
}
