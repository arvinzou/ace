package com.huacainfo.ace.uf.web.controller;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.uf.model.Feedback;
import com.huacainfo.ace.uf.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.model.Organization;
import com.huacainfo.ace.uf.service.OrganizationService;
import com.huacainfo.ace.uf.vo.OrganizationQVo;
import com.huacainfo.ace.uf.vo.OrganizationVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/www/")
public class WWWController extends UfBaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	private String[] areas;

	@RequestMapping(value = "/selectOrganizationList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationList(String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationList(q,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganizationListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationListMap() throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationListMap(this.getCurWxUser());
	}

	@RequestMapping(value = "/selectOrganization.do")
	@ResponseBody
	public Map<String,Object> selectOrganization(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganization(id,this.getCurWxUser());
	}

	@RequestMapping(value = "/selectAreaCodeList.do")
	@ResponseBody
	public List<Map<String,Object>> selectAreaCodeList(String areaCode) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		List<Map<String,Object>> list=this.organizationService.selectAreaCodeList(areaCode,this.getCurWxUser());
				areas=new String[list.size()];
		int i=0;
		for(Map<String,Object> e:list){
			areas[i]=(String) e.get("id");
			i++;
		}
		return list;
	}

	@RequestMapping(value = "/insertFeedback.do")
	@ResponseBody
	public MessageResponse insertFeedback(Feedback obj,String captcha) throws Exception {
		this.logger.info("{}",obj);
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		obj.setAreaCode(areas[Integer.valueOf(obj.getAreaCode())]);
		if(CommonUtils.isBlank(captcha)){
			return new MessageResponse(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new MessageResponse(1,"验证码错误！");
		}
		return this.feedbackService
				.insertFeedback(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectOrganizationCategoryList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationCategoryList() throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationCategoryList(this.getCurWxUser());
	}

	@RequestMapping(value = "/selectOrganizationByCategory.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationByCategory(String category) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationByCategory(category,this.getCurWxUser());
	}
}
