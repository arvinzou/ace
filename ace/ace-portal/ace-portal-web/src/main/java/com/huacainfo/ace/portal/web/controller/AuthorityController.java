package com.huacainfo.ace.portal.web.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DictUtils;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.SystemService;

@Controller
@RequestMapping("/www/")
public class AuthorityController extends PortalBaseController{
	private static final long serialVersionUID = 1L;
	private static final String SESSION_USER_RESOURCES="SESSION_USER_RESOURCES";
	@Autowired
	private AuthorityService authorityService;

	Logger logger = LoggerFactory.getLogger(this.getClass());



	@RequestMapping(value = "/authority.do")
	@ResponseBody
	public SingleResult<Map<String,String>> authority(String appid,String appsecret,String code,String encryptedData,String iv)throws Exception {
		SingleResult<Map<String,String>> rst= this.authorityService.authority(appid,appsecret,code,encryptedData,iv);
		if(rst.getStatus()==0){
			Map<String,String> o=rst.getValue();
			String session_key=o.get("session_key");
			String openid=o.get("openid");
			String _3rd_session=o.get("3rd_session");
			this.getRequest().getSession().setAttribute(_3rd_session,session_key+openid);
			rst.getValue().remove("session_key");
			rst.getValue().remove("openid");
		}
		return rst;
	}

	@RequestMapping(value = "/request.do")
	@ResponseBody
	public WxUser request()throws Exception {
		WxUser wxUser=this.getCurWxUser();
		logger.info("wxUser : {}",wxUser);
		return wxUser;
	}
}
