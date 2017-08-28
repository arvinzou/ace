package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.service.RemttanceInfoService;
import com.huacainfo.ace.gesp.vo.RemttanceInfoQVo;
import com.huacainfo.ace.gesp.vo.RemttanceInfoVo;
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
import com.huacainfo.ace.gesp.model.RemttanceInfo;

@Controller
@RequestMapping("/remttanceInfo")
public class RemttanceInfoController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RemttanceInfoService remttanceInfoService;

	@RequestMapping(value = "/findRemttanceInfoList.do")
	@ResponseBody
	public PageResult<RemttanceInfoVo> findRemttanceInfoList(RemttanceInfoQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getMemberCode())){
			condition.setMemberCode(this.getCurUserProp().getCorpId());
		}
		PageResult<RemttanceInfoVo> rst = this.remttanceInfoService
				.findRemttanceInfoList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertRemttanceInfo.do")
	@ResponseBody
	public MessageResponse insertRemttanceInfo(String jsons) throws Exception {
		RemttanceInfo obj = JSON.parseObject(jsons, RemttanceInfo.class);
		return this.remttanceInfoService
				.insertRemttanceInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateRemttanceInfo.do")
	@ResponseBody
	public MessageResponse updateRemttanceInfo(String jsons) throws Exception {
		RemttanceInfo obj = JSON.parseObject(jsons, RemttanceInfo.class);
		return this.remttanceInfoService
				.updateRemttanceInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectRemttanceInfoByPrimaryKey.do")
	@ResponseBody
	public SingleResult<RemttanceInfo> selectRemttanceInfoByPrimaryKey(String id)
			throws Exception {
		return this.remttanceInfoService.selectRemttanceInfoByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteRemttanceInfoByRemttanceInfoId.do")
	@ResponseBody
	public MessageResponse deleteRemttanceInfoByRemttanceInfoId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.remttanceInfoService.deleteRemttanceInfoByRemttanceInfoId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByDeptId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByDeptId(String deptId)
			throws Exception {
		if (CommonUtils.isBlank(deptId)) {
			deptId=this.getCurUserProp().getCorpId();
		}
		return this.remttanceInfoService.selectListByDeptId(deptId).getValue();
	}
}
