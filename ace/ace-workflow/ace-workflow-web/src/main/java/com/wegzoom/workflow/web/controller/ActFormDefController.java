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
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.workflow.model.ActFormDef;
import com.huacainfo.ace.workflow.service.ActFormDefService;
import com.huacainfo.ace.workflow.vo.ActFormDefQVo;
import com.huacainfo.ace.workflow.vo.ActFormDefVo;

@Controller
@RequestMapping("/actFormDef")
public class ActFormDefController extends WorkflowBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormDefService actFormDefService;

	@RequestMapping(value = "/findActFormDefList.do")
	@ResponseBody
	public PageResult<ActFormDefVo> findActFormDefList(ActFormDefQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActFormDefVo> rst = this.actFormDefService
				.findActFormDefList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActFormDef.do")
	@ResponseBody
	public MessageResponse insertActFormDef(String jsons) throws Exception {
		ActFormDef obj = JSON.parseObject(jsons, ActFormDef.class);
		return this.actFormDefService
				.insertActFormDef(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActFormDef.do")
	@ResponseBody
	public MessageResponse updateActFormDef(String jsons) throws Exception {
		ActFormDef obj = JSON.parseObject(jsons, ActFormDef.class);
		return this.actFormDefService
				.updateActFormDef(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActFormDefByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActFormDef> selectActFormDefByPrimaryKey(String id)
			throws Exception {
		return this.actFormDefService.selectActFormDefByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActFormDefByActFormDefId.do")
	@ResponseBody
	public MessageResponse deleteActFormDefByActFormDefId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.actFormDefService.deleteActFormDefByActFormDefId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByDeptId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByDeptId(String deptId)
			throws Exception {
		if (CommonUtils.isBlank(deptId)) {
			deptId=this.getCurUserProp().getCorpId();
		}
		return this.actFormDefService.selectListByDeptId(deptId).getValue();
	}
	
	@RequestMapping(value = "/selectFormDefList.do")
	@ResponseBody
	public Map<String,Object> selectFormDefList(String q,String id)throws Exception {
		Map<String,String> params=new HashMap<String,String>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		return this.actFormDefService.selectFormDefList(params);
	}
}
