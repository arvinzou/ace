package com.huacainfo.ace.rvc.web.controller;

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
import com.huacainfo.ace.uf.model.PerCategory;
import com.huacainfo.ace.uf.service.PerCategoryService;
import com.huacainfo.ace.uf.vo.PerCategoryVo;
import com.huacainfo.ace.uf.vo.PerCategoryQVo;

@Controller
@RequestMapping("/perCategory")
public class PerCategoryController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerCategoryService perCategoryService;

	@RequestMapping(value = "/findPerCategoryList.do")
	@ResponseBody
	public PageResult<PerCategoryVo> findPerCategoryList(PerCategoryQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerCategoryVo> rst = this.perCategoryService
				.findPerCategoryList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerCategory.do")
	@ResponseBody
	public MessageResponse insertPerCategory(String jsons) throws Exception {
		PerCategory obj = JSON.parseObject(jsons, PerCategory.class);
		return this.perCategoryService
				.insertPerCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerCategory.do")
	@ResponseBody
	public MessageResponse updatePerCategory(String jsons) throws Exception {
		PerCategory obj = JSON.parseObject(jsons, PerCategory.class);
		return this.perCategoryService
				.updatePerCategory(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerCategoryByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerCategoryVo> selectPerCategoryByPrimaryKey(String id)
			throws Exception {
		return this.perCategoryService.selectPerCategoryByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerCategoryByPerCategoryId.do")
	@ResponseBody
	public MessageResponse deletePerCategoryByPerCategoryId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perCategoryService.deletePerCategoryByPerCategoryId(id,
				this.getCurUserProp());
	}
}
