package com.huacainfo.ace.uf.web.controller;

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
import com.huacainfo.ace.uf.model.PerDept;
import com.huacainfo.ace.uf.service.PerDeptService;
import com.huacainfo.ace.uf.vo.PerDeptVo;
import com.huacainfo.ace.uf.vo.PerDeptQVo;

@Controller
@RequestMapping("/perDept")
public class PerDeptController extends UfBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerDeptService perDeptService;

	@RequestMapping(value = "/findPerDeptList.do")
	@ResponseBody
	public PageResult<PerDeptVo> findPerDeptList(PerDeptQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<PerDeptVo> rst = this.perDeptService
				.findPerDeptList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPerDept.do")
	@ResponseBody
	public MessageResponse insertPerDept(String jsons) throws Exception {
		PerDept obj = JSON.parseObject(jsons, PerDept.class);
		return this.perDeptService
				.insertPerDept(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePerDept.do")
	@ResponseBody
	public MessageResponse updatePerDept(String jsons) throws Exception {
		PerDept obj = JSON.parseObject(jsons, PerDept.class);
		return this.perDeptService
				.updatePerDept(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPerDeptByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PerDeptVo> selectPerDeptByPrimaryKey(String id)
			throws Exception {
		return this.perDeptService.selectPerDeptByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePerDeptByPerDeptId.do")
	@ResponseBody
	public MessageResponse deletePerDeptByPerDeptId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.perDeptService.deletePerDeptByPerDeptId(id,
				this.getCurUserProp());
	}
}
