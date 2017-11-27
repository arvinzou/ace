package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.portal.vo.WxUserVo;
import com.huacainfo.ace.portal.vo.WxUserQVo;

import java.util.List;
import java.util.Map;

public interface WxUserService {
	public abstract PageResult<WxUserVo> findWxUserList(WxUserQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertWxUser(WxUser obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateWxUser(WxUser obj,UserProp userProp) throws Exception;
	public abstract SingleResult<WxUser> selectWxUserByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteWxUserByWxUserId(String id,UserProp userProp) throws Exception;
	public abstract MessageResponse deleteRoleById(String id,UserProp userProp) throws Exception;
	public abstract MessageResponse updateRoleById(String id,String role,UserProp userProp) throws Exception;
	List<Map<String,Object>> selectWxUser(Map<String,Object> condition)throws Exception;

	public abstract MessageResponse updateFaceToken(String image_url,String unionId) throws Exception;
	public abstract SingleResult<WxUser> searchFace(String image_url) throws Exception;

}
