package com.huacainfo.ace.gesp.web.controller;

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
import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.service.MemberLevelService;
import com.huacainfo.ace.gesp.vo.MemberLevelQVo;
import com.huacainfo.ace.gesp.vo.MemberLevelVo;

@Controller
@RequestMapping("/memberLevel")
public class MemberLevelController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberLevelService memberLevelService;

	@RequestMapping(value = "/findMemberLevelList.do")
	@ResponseBody
	public PageResult<MemberLevelVo> findMemberLevelList(MemberLevelQVo condition,
			PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getMemberCode())){
			condition.setMemberCode(this.getCurUserProp().getCorpId());
		}
		String code = condition.getMemberCode().toString();
		if(code.length()<5){
			condition.setMemberCode("5");
		}
		PageResult<MemberLevelVo> rst = this.memberLevelService
				.findMemberLevelList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertMemberLevel.do")
	@ResponseBody
	public MessageResponse insertMemberLevel(String jsons) throws Exception {
		MemberLevel obj = JSON.parseObject(jsons, MemberLevel.class);
		return this.memberLevelService
				.insertMemberLevel(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateMemberLevel.do")
	@ResponseBody
	public MessageResponse updateMemberLevel(String jsons) throws Exception {
		MemberLevel obj = JSON.parseObject(jsons, MemberLevel.class);
		return this.memberLevelService
				.updateMemberLevel(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectMemberLevelByPrimaryKey.do")
	@ResponseBody
	public SingleResult<MemberLevel> selectMemberLevelByPrimaryKey(String id)
			throws Exception {
		return this.memberLevelService.selectMemberLevelByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteMemberLevelByMemberLevelId.do")
	@ResponseBody
	public MessageResponse deleteMemberLevelByMemberLevelId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.memberLevelService.deleteMemberLevelByMemberLevelId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByDeptId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByDeptId(String deptId,String selected)
			throws Exception {
		if (CommonUtils.isBlank(deptId)) {
			deptId=this.getCurUserProp().getCorpId();
		}
		return this.memberLevelService.selectListByDeptId(deptId,selected).getValue();
	}
}
