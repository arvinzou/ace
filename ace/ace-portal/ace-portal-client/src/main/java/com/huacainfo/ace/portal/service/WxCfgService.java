package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.model.WxFormid;
import com.huacainfo.ace.portal.vo.WxCfgVo;
import com.huacainfo.ace.portal.vo.WxCfgQVo;
import java.util.Map;
import java.util.List;
public interface WxCfgService {

	public abstract PageResult<WxCfgVo> findWxCfgList(WxCfgQVo condition, int start, int limit, String orderBy)
			throws Exception;
	public abstract MessageResponse insertWxCfg(WxCfg obj, UserProp userProp) throws Exception;
	public abstract MessageResponse updateWxCfg(WxCfg obj, UserProp userProp) throws Exception;
	public abstract SingleResult<WxCfgVo> selectWxCfgByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteWxCfgByWxCfgId(String id, UserProp userProp) throws Exception;

	public abstract void updateAccessToken(String appid,String accessToken,int expiresIn);

	public abstract List<Map<String,Object>> selectAppList();

	public abstract MessageResponse insertFormIds(List<WxFormid> list);

	public abstract SingleResult<String> selectAccessTokenByDeptId(String deptId) throws Exception;

	public abstract SingleResult<String> getTicket(String access_token) throws Exception;

	public abstract SingleResult<String> getSignature(String url, String accessToken) throws Exception;

}
