package com.huacainfo.ace.portal.web.controller;

import java.util.*;
import java.util.Date;
import java.util.Calendar;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.WxFormid;
import com.huacainfo.ace.portal.service.AuthorityService;
import com.huacainfo.ace.portal.service.WxCfgService;
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


	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	@Autowired
	private WxCfgService wxCfgService;



	@RequestMapping(value = "/authority.do")
	@ResponseBody
	public SingleResult<Map<String,Object>> authority(String appid,String appsecret,String code,String encryptedData,String iv,String latitude,String longitude)throws Exception {
		SingleResult<Map<String,Object>> rst= this.authorityService.authority(appid,appsecret,code,encryptedData,iv,latitude,longitude);
		if(rst.getStatus()==0){
			Map<String,Object> o=rst.getValue();
			String session_key=(String) o.get("session_key");
			String openid=(String)o.get("openid");
			String _3rd_session=(String)o.get("3rd_session");
			this.getRequest().getSession().setAttribute(_3rd_session,session_key+openid);
			rst.getValue().remove("session_key");
			rst.getValue().remove("openid");
		}
		return rst;
	}

	@RequestMapping(value = "/bind.do")
	@ResponseBody
	public SingleResult<Map<String,Object>> bind(String captcha,String mobile)throws Exception {
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		if(CommonUtils.isBlank(captcha)){
			return new SingleResult(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new SingleResult(1,"验证码错误！");
		}
		SingleResult<Map<String,Object>> rst= this.authorityService.bind(_3rd_session,mobile);
		return rst;
	}

	@RequestMapping(value = "/reg.do")
	@ResponseBody
	public SingleResult<WxUser> reg(String mobile,String addr,String email,String name,String captcha,String formId)throws Exception {
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		if(CommonUtils.isBlank(captcha)){
			return new SingleResult(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new SingleResult(1,"验证码错误！");
		}
		WxUser wxUser=this.getCurWxUser();
		wxUser.setMobile(mobile);
		wxUser.setAddr(addr);
		wxUser.setEmail(email);
		wxUser.setName(name);
		logger.info("wxUser : {}",wxUser);

		WxFormid formid=new WxFormid();
		formid.setOpenId(this.getCurWxUser().getOpenId());
		formid.setFormId(formId);
		formid.setStatus("0");
		formid.setCreateDate(new Date());

		List<WxFormid> list=new ArrayList<WxFormid>();
		list.add(formid);
		this.wxCfgService.insertFormIds(list);
		return this.authorityService.reg(wxUser);
	}

	@RequestMapping(value = "/getPhoneNumber.do")
	@ResponseBody
	public SingleResult<Map<String,String>> getPhoneNumber(String appid,String appsecret,String code,String encryptedData,String iv)throws Exception {
		SingleResult<Map<String,String>> rst= this.authorityService.getPhoneNumber(appid,appsecret,code,encryptedData,iv);
		return rst;
	}
	@RequestMapping(value = "/updateForExperienceUser.do")
	@ResponseBody
	public  SingleResult<WxUser> updateForExperienceUser(String code,String name) throws Exception{
		WxUser user=this.getCurWxUser();
		if(user==null){
			return new SingleResult<WxUser>(1,"未注册的微信用户。");
		}
		if(CommonUtils.isBlank(name)){
			return new SingleResult<WxUser>(1,"姓名不能为空。");
		}
		if(CommonUtils.isBlank(code)){
			return new SingleResult<WxUser>(1,"验证码不能为空。");
		}
		if(!code.equals(this.getNatiaveCode())){
			return new SingleResult<WxUser>(1,"验证码错误。");
		}
		this.authorityService.updateForExperienceUser(user.getUnionId(),name);
		return this.authorityService.selectForExperienceUser(user.getUnionId());
	}

	@RequestMapping(value = "/selectForExperienceUser.do")
	@ResponseBody
	public  SingleResult<WxUser> selectForExperienceUser(String id) throws Exception{
		return this.authorityService.selectForExperienceUser(id);
	}

	private String getNatiaveCode(){
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour+""+date;
	}

	public static void main(String args[]){
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(hour+""+date) ;
	}
}
