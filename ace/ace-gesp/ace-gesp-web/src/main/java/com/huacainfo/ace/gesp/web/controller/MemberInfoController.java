package com.huacainfo.ace.gesp.web.controller;

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
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.service.MemberInfoService;
import com.huacainfo.ace.gesp.vo.MemberInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberInfoVo;

@Controller
@RequestMapping("/memberInfo")
public class MemberInfoController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberInfoService memberInfoService;

	@RequestMapping(value = "/findMemberInfoList.do")
	@ResponseBody
	public PageResult<MemberInfoVo> findMemberInfoList(MemberInfoQVo condition,
			PageParamNoChangeSord page) throws Exception {
		String code = this.getCurUserProp().getCorpId();
		condition.setMemberCode(code);
		if(code.length()<5){
			condition.setMemberCode("5");
		}
		logger.debug("会员信息memberCode{},{}",condition.getMemberCode(), condition.getCreateUserId());
		PageResult<MemberInfoVo> rst = this.memberInfoService
				.findMemberInfoList(condition, page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}

	@RequestMapping(value = "/insertMemberInfo.do")
	@ResponseBody
	public MessageResponse insertMemberInfo(String jsons) throws Exception {
		MemberInfo obj = JSON.parseObject(jsons, MemberInfo.class);
		if (CommonUtils.isBlank(obj.getMemberCode())) {
			obj.setMemberCode(this.getCurUserProp().getCorpId());
		}
		return this.memberInfoService.insertMemberInfo(obj,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/updateAudit.do")
	@ResponseBody
	public MessageResponse updateAudit(String jsons) throws Exception {
		MemberInfo obj = JSON.parseObject(jsons, MemberInfo.class);
		return this.memberInfoService.updateAudit(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectMemberInfoByPrimaryKey.do")
	@ResponseBody
	public SingleResult<MemberInfo> selectMemberInfoByPrimaryKey(String id)
			throws Exception {
		return this.memberInfoService.selectMemberInfoByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteMemberInfoByMemberInfoId.do")
	@ResponseBody
	public MessageResponse deleteMemberInfoByMemberInfoId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.memberInfoService.deleteMemberInfoByMemberInfoId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/loadMemberBaseInfo.do")
	@ResponseBody
	public SingleResult<Department> loadMemberBaseInfo(String id, String parentDepartmentId)
			throws Exception {
		return this.memberInfoService.loadMemberBaseInfo(id, parentDepartmentId);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective.do")
	@ResponseBody
	public MessageResponse updateByPrimaryKeySelective(String jsons)
			throws Exception {
		Department obj = JSON.parseObject(jsons, Department.class);
		if(CommonUtils.isBlank(obj.getDepartmentId())){
			obj.setDepartmentId(this.getCurUserProp().getCorpId());
		}
		return this.memberInfoService.updateByPrimaryKeySelective(obj,
				this.getCurUserProp());
	}
	
	/**
	 * 
	 * @param jsons
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertMemberInfoByAdmin.do")
	@ResponseBody
	public MessageResponse insertMemberInfoByAdmin(String jsons) throws Exception {
		MemberInfo obj = JSON.parseObject(jsons, MemberInfo.class);
		if (CommonUtils.isBlank(obj.getMemberCode())) {
			obj.setMemberCode(this.getCurUserProp().getCorpId());
		}
		MessageResponse mess;
		String stat = obj.getStatus();
		if(!CommonUtils.isBlank(stat)&&"20".equals(stat)){
			mess = this.memberInfoService.updateMemberBaseByPrimaryKey(obj, 
					this.getCurUserProp());
		}else{
			mess = this.memberInfoService.insertMemberInfoByAdmin(obj,
					this.getCurUserProp());
		}
		return mess;
	}
	
	@RequestMapping(value = "/updateMemberInfo.do")
	@ResponseBody
	public MessageResponse updateMemberInfo(String jsons)
			throws Exception {
		Department obj = JSON.parseObject(jsons, Department.class);
		if(CommonUtils.isBlank(obj.getDepartmentId())){
			obj.setDepartmentId(this.getCurUserProp().getCorpId());
		}
		return this.memberInfoService.updateByPrimaryKeySelective(obj,
				this.getCurUserProp());
	}
	@RequestMapping(value = "/updateMemberBaseByPrimaryKey.do")
	@ResponseBody
	public MessageResponse updateMemberBaseByPrimaryKey(String jsons)
			throws Exception {
		MemberInfo obj = JSON.parseObject(jsons, MemberInfo.class);
		if (CommonUtils.isBlank(obj.getMemberCode())) {
			obj.setMemberCode(this.getCurUserProp().getCorpId());
		}
		return this.memberInfoService.updateMemberBaseByPrimaryKey(obj,
				this.getCurUserProp());
	}

	/**
	 * 会员退会
	 * 
	 * @param jsons
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateState.do")
	@ResponseBody
	public MessageResponse updateState(String jsons) throws Exception {
		MemberInfo obj = JSON.parseObject(jsons, MemberInfo.class);
		obj.setStatus("8");//状态(1:正常，8:已退会)
		return memberInfoService.updateState(obj, this.getCurUserProp());
	}

}
