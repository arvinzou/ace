package com.huacainfo.ace.uf.web.controller;

import com.huacainfo.ace.common.model.WxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value = "/selectOrganizationList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationList() throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationList(this.getCurWxUser());
	}

	@RequestMapping(value = "/selectOrganization.do")
	@ResponseBody
	public Map<String,Object> selectOrganization(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganization(id,this.getCurWxUser());
	}
}
