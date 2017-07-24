package com.huacainfo.ace.uf.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.result.ListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.model.Feedback;
import com.huacainfo.ace.uf.service.AnalysisService;
import com.huacainfo.ace.uf.service.FeedbackService;
import com.huacainfo.ace.uf.service.OrganizationService;
import com.huacainfo.ace.uf.service.PersonageService;
import com.huacainfo.ace.uf.service.DeptService;
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
	private PersonageService personageService;


	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	private String[] areas;
	/*统战服务*/
	@RequestMapping(value = "/selectOrganizationList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationList(String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationList(q,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganizationListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationListMap(String longitude,String latitude) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationListMap(this.getCurWxUser(),longitude,latitude);
	}
	@RequestMapping(value = "/selectOrganizationCategoryList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationCategoryList() throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationCategoryList(this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganization.do")
	@ResponseBody
	public Map<String,Object> selectOrganization(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganization(id,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganizationByCategory.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationByCategory(String category) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationByCategory(category,this.getCurWxUser());
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
	/*建言献策*/
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
	@RequestMapping(value = "/query.do")
	@ResponseBody
	public ListResult<Map<String,Object>> query(
			String reportId, int start, int limit)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(start)) {
			start = 0;
		}
		if (CommonUtils.isBlank(limit)) {
			limit = 10;
		}
		return analysisService.query(condition, reportId, start, limit);
	}

	/*统战机构*/
	@RequestMapping(value = "/selectDeptList.do")
	@ResponseBody
	public List<Map<String,Object>> selectDeptList(String q,String areaCode) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDeptList(q,this.getCurWxUser(),areaCode);
	}
	@RequestMapping(value = "/selectDeptListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectDeptListMap(String longitude,String latitude) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDeptListMap(this.getCurWxUser(),longitude,latitude);
	}
	@RequestMapping(value = "/selectDept.do")
	@ResponseBody
	public Map<String,Object> selectDept(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDept(id,this.getCurWxUser());
	}

	/*统战人士*/
	@RequestMapping(value = "/selectPersonageListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectPersonageListMap(String longitude,String latitude) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageListMap(longitude,latitude);
	}
	@RequestMapping(value = "/selectPersonageList.do")
	@ResponseBody
	public List<Map<String,Object>> selectPersonageList(String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageList(q,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectPersonage.do")
	@ResponseBody
	public Map<String,Object> selectPersonage(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageById(id);
	}
}
