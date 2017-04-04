package com.huacainfo.workflow.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.workflow.model.ActFormCategory;
import com.huacainfo.ace.workflow.service.ActFormCategoryService;
import com.huacainfo.ace.workflow.vo.ActFormCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActFormCategoryVo;

@Controller
@RequestMapping("/actFormCategory")
public class ActFormCategoryController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormCategoryService actFormCategoryService;

	@RequestMapping(value = "/findActFormCategoryList.do")
	@ResponseBody
	public PageResult<ActFormCategoryVo> findActFormCategoryList(ActFormCategoryQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActFormCategoryVo> rst = this.actFormCategoryService
				.findActFormCategoryList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActFormCategory.do")
	@ResponseBody
	public MessageResponse insertActFormCategory(String jsons) throws Exception {
		ActFormCategory obj = JSON.parseObject(jsons, ActFormCategory.class);
		return this.actFormCategoryService
				.insertActFormCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActFormCategory.do")
	@ResponseBody
	public MessageResponse updateActFormCategory(String jsons) throws Exception {
		ActFormCategory obj = JSON.parseObject(jsons, ActFormCategory.class);
		return this.actFormCategoryService
				.updateActFormCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActFormCategoryByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActFormCategory> selectActFormCategoryByPrimaryKey(String id)
			throws Exception {
		return this.actFormCategoryService.selectActFormCategoryByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActFormCategoryByActFormCategoryId.do")
	@ResponseBody
	public MessageResponse deleteActFormCategoryByActFormCategoryId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.actFormCategoryService.deleteActFormCategoryByActFormCategoryId(id,
				this.getCurUserProp());
	}
	@RequestMapping(value = "/selectTreeList.do")
	@ResponseBody
	public List<Tree> selectTreeList() throws Exception {
		return this.actFormCategoryService.selectTreeList();
	}
}
