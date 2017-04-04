package com.huacainfo.workflow.web.controller;

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
import com.huacainfo.ace.workflow.model.ActFormFieldType;
import com.huacainfo.ace.workflow.service.ActFormFieldTypeService;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeQVo;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeVo;

@Controller
@RequestMapping("/actFormFieldType")
public class ActFormFieldTypeController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormFieldTypeService actFormFieldTypeService;

	/**
	 * 
	 * @Title:findActFormFieldTypeList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param condition
	 * @param: @param page
	 * @param: @return
	 * @param: @throws Exception
	 * @return: PageResult<ActFormFieldTypeVo>
	 * @throws
	 * @author: chenxiaoke
	 * @version: 2017年2月15日 上午11:27:00
	 */
	@RequestMapping(value = "/findActFormFieldTypeList.do")
	@ResponseBody
	public PageResult<ActFormFieldTypeVo> findActFormFieldTypeList(
			ActFormFieldTypeQVo condition, PageParamNoChangeSord page)
			throws Exception {
		PageResult<ActFormFieldTypeVo> rst = this.actFormFieldTypeService
				.findActFormFieldTypeList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}

		return rst;
	}

	@RequestMapping(value = "/insertActFormFieldType.do")
	@ResponseBody
	public MessageResponse insertActFormFieldType(String jsons)
			throws Exception {
		ActFormFieldType obj = JSON.parseObject(jsons, ActFormFieldType.class);
		return this.actFormFieldTypeService.insertActFormFieldType(obj,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActFormFieldType.do")
	@ResponseBody
	public MessageResponse updateActFormFieldType(String jsons)
			throws Exception {
		ActFormFieldType obj = JSON.parseObject(jsons, ActFormFieldType.class);
		return this.actFormFieldTypeService.updateActFormFieldType(obj,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActFormFieldTypeByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActFormFieldType> selectActFormFieldTypeByPrimaryKey(
			String id) throws Exception {
		return this.actFormFieldTypeService
				.selectActFormFieldTypeByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActFormFieldTypeByActFormFieldTypeId.do")
	@ResponseBody
	public MessageResponse deleteActFormFieldTypeByActFormFieldTypeId(
			String jsons) throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.actFormFieldTypeService
				.deleteActFormFieldTypeByActFormFieldTypeId(id,
						this.getCurUserProp());
	}
}
