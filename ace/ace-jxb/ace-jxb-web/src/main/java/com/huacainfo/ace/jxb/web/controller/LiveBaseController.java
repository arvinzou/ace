package com.huacainfo.ace.jxb.web.controller;

import java.io.Serializable;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import com.huacainfo.ace.portal.service.AuthorityService;
public class LiveBaseController extends BaseController implements
		Serializable {
	private static final long serialVersionUID = 1L;
	public int defaultPageSize = 10;

	@Autowired
	private AuthorityService authorityService;

	public Map<String, Object> getPageParam(int page, Map<String, Object> p) {
		if (page <= 1) {
			p.put("start", 0);
			p.put("end", defaultPageSize);
		} else {
			p.put("start", (page - 1) * defaultPageSize);
			p.put("end",  defaultPageSize);
		}
		p.put("fastdfs_server", PropertyUtil.getProperty("fastdfs_server"));
		return p;
	}
	protected SingleResult<UserProp> getCurUserPropByOpenId() throws Exception{
		String openId=null;
		if(CommonUtils.isNotEmpty(this.getCurWxUser())){
			openId=this.getCurWxUser().getOpenId();
		}
		if(CommonUtils.isNotEmpty(this.getCurUserinfo())){
			openId=this.getCurUserinfo().getOpenid();
		}
		SingleResult<UserProp> rst=authorityService.getCurUserPropByOpenId(openId);
		return rst;
	}
	
}
