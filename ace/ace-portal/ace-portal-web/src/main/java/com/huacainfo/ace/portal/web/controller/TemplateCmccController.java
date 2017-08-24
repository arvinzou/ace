package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.PageResult;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.portal.model.TemplateCmcc;
import com.huacainfo.ace.portal.service.TemplateCmccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.huacainfo.ace.portal.vo.TemplateCmccQVo;
import com.huacainfo.ace.portal.vo.TemplateCmccVo;

@Controller
@RequestMapping("/templateCmcc")
public class TemplateCmccController extends PortalBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateCmccService templateCmccService;
	@RequestMapping(value = "/findTemplateCmccList.do")
	@ResponseBody
	public PageResult<TemplateCmccVo> findTemplateCmccList(TemplateCmccQVo condition, PageParam page) throws Exception {
		PageResult<TemplateCmccVo> rst = this.templateCmccService.findTemplateCmccList(condition, page.getStart(),
				page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	@RequestMapping(value = "/insertTemplateCmcc.do")
	@ResponseBody
	public MessageResponse insertTemplateCmcc(String jsons) throws Exception {
		TemplateCmcc o = JSON.parseObject(jsons, TemplateCmcc.class);
		return this.templateCmccService.insertTemplateCmcc(o, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateTemplateCmcc.do")
	@ResponseBody
	public MessageResponse updateTemplateCmcc(String jsons) throws Exception {
		TemplateCmcc o = JSON.parseObject(jsons, TemplateCmcc.class);
		return this.templateCmccService.updateTemplateCmcc(o, this.getCurUserProp());
	}

	@RequestMapping(value = "/deleteTemplateCmccByTemplateCmccId.do")
	@ResponseBody
	public MessageResponse deleteTemplateCmccByTemplateCmccId(String id) throws Exception {
		return this.templateCmccService.deleteTemplateCmccByTemplateCmccId(id, this.getCurUserProp());
	}
}
