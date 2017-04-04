package com.huacainfo.workflow.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.workflow.model.ActProdefCustom;
import com.huacainfo.ace.workflow.service.ActProdefCustomService;
import com.huacainfo.ace.workflow.vo.ActProdefCustomQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCustomVo;

@Controller
@RequestMapping("/actProdefCustom")
public class ActProdefCustomController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActProdefCustomService actProdefCustomService;

	@RequestMapping(value = "/findActProdefCustomList.do")
	@ResponseBody
	public PageResult<ActProdefCustomVo> findActProdefCustomList(ActProdefCustomQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActProdefCustomVo> rst = this.actProdefCustomService
				.findActProdefCustomList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActProdefCustom.do")
	@ResponseBody
	public MessageResponse insertActProdefCustom(String jsons) throws Exception {
		ActProdefCustom obj = JSON.parseObject(jsons, ActProdefCustom.class);
		return this.actProdefCustomService
				.insertActProdefCustom(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActProdefCustom.do")
	@ResponseBody
	public MessageResponse updateActProdefCustom(String jsons) throws Exception {
		ActProdefCustom obj = JSON.parseObject(jsons, ActProdefCustom.class);
		return this.actProdefCustomService
				.updateActProdefCustom(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActProdefCustomByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActProdefCustom> selectActProdefCustomByPrimaryKey(String id)
			throws Exception {
		return this.actProdefCustomService.selectActProdefCustomByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActProdefCustomByActProdefCustomId.do")
	@ResponseBody
	public MessageResponse deleteActProdefCustomByActProdefCustomId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.actProdefCustomService.deleteActProdefCustomByActProdefCustomId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectTreeList.do")
	@ResponseBody
	public List<Tree> selectTreeList() throws Exception {
		return this.actProdefCustomService.selectTreeList();
	}
	
	@RequestMapping(value = "/selectGridList.do")
	@ResponseBody
	public Map<String,Object> selectGridList(String q,String id)throws Exception {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		return this.actProdefCustomService.selectGridList(params);
	}
}
