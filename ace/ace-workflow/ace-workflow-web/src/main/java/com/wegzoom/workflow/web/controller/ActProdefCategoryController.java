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
import com.huacainfo.ace.workflow.model.ActProdefCategory;
import com.huacainfo.ace.workflow.service.ActProdefCategoryService;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryVo;

@Controller
@RequestMapping("/actProdefCategory")
public class ActProdefCategoryController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActProdefCategoryService actProdefCategoryService;

	@RequestMapping(value = "/findActProdefCategoryList.do")
	@ResponseBody
	public PageResult<ActProdefCategoryVo> findActProdefCategoryList(ActProdefCategoryQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActProdefCategoryVo> rst = this.actProdefCategoryService
				.findActProdefCategoryList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActProdefCategory.do")
	@ResponseBody
	public MessageResponse insertActProdefCategory(String jsons) throws Exception {
		ActProdefCategory obj = JSON.parseObject(jsons, ActProdefCategory.class);
		return this.actProdefCategoryService
				.insertActProdefCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActProdefCategory.do")
	@ResponseBody
	public MessageResponse updateActProdefCategory(String jsons) throws Exception {
		ActProdefCategory obj = JSON.parseObject(jsons, ActProdefCategory.class);
		return this.actProdefCategoryService
				.updateActProdefCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActProdefCategoryByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActProdefCategory> selectActProdefCategoryByPrimaryKey(String id)
			throws Exception {
		return this.actProdefCategoryService.selectActProdefCategoryByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActProdefCategoryByActProdefCategoryId.do")
	@ResponseBody
	public MessageResponse deleteActProdefCategoryByActProdefCategoryId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.actProdefCategoryService.deleteActProdefCategoryByActProdefCategoryId(id,
				this.getCurUserProp());
	}
	@RequestMapping(value = "/selectTreeList.do")
	@ResponseBody
	public List<Tree> selectTreeList() throws Exception {
		return this.actProdefCategoryService.selectTreeList();
	}
}
