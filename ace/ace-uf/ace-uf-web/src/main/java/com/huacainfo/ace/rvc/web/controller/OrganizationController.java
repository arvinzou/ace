package com.huacainfo.ace.rvc.web.controller;

import com.huacainfo.ace.common.tools.CommonUtils;
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
import com.huacainfo.ace.uf.model.Organization;
import com.huacainfo.ace.uf.service.OrganizationService;
import com.huacainfo.ace.uf.vo.OrganizationVo;
import com.huacainfo.ace.uf.vo.OrganizationQVo;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/findOrganizationList.do")
	@ResponseBody
	public PageResult<OrganizationVo> findOrganizationList(OrganizationQVo condition, PageParamNoChangeSord page)
			throws Exception {
		if(CommonUtils.isBlank(condition.getAreaCode())){
			condition.setAreaCode(this.getCurUserProp().getAreaCode());
		}
		PageResult<OrganizationVo> rst = this.organizationService.findOrganizationList(condition, page.getStart(),
				page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertOrganization.do")
	@ResponseBody
	public MessageResponse insertOrganization(String jsons) throws Exception {
		Organization obj = JSON.parseObject(jsons, Organization.class);
		return this.organizationService.insertOrganization(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateOrganization.do")
	@ResponseBody
	public MessageResponse updateOrganization(String jsons) throws Exception {
		Organization obj = JSON.parseObject(jsons, Organization.class);
		return this.organizationService.updateOrganization(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectOrganizationByPrimaryKey.do")
	@ResponseBody
	public SingleResult<OrganizationVo> selectOrganizationByPrimaryKey(String id) throws Exception {
		return this.organizationService.selectOrganizationByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteOrganizationByOrganizationId.do")
	@ResponseBody
	public MessageResponse deleteOrganizationByOrganizationId(String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.organizationService.deleteOrganizationByOrganizationId(id, this.getCurUserProp());
	}
}
