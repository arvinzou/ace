package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.MemberPayInfo;
import com.huacainfo.ace.gesp.service.MemberPayInfoService;
import com.huacainfo.ace.gesp.vo.MemberPayInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberPayInfoVo;
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

@Controller
@RequestMapping("/memberPayInfo")
public class MemberPayInfoController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberPayInfoService memberPayInfoService;

	@RequestMapping(value = "/findMemberPayInfoList.do")
	@ResponseBody
	public PageResult<MemberPayInfoVo> findMemberPayInfoList(MemberPayInfoQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getMemberCode())){
			condition.setMemberCode(this.getCurUserProp().getCorpId());
		}
		String code = condition.getMemberCode();
		if(code.length()<5){
			condition.setMemberCode("5");
		}
		PageResult<MemberPayInfoVo> rst = this.memberPayInfoService
				.findMemberPayInfoList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	/**
	 * 根据编号查询
	 * 
	 * @param id
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByPrimarkey.do")
	@ResponseBody
	public MemberPayInfo selectByPrimarkey(String id) throws Exception {
		return this.memberPayInfoService.selectByPrimarkey(id);
	}
	
	@RequestMapping(value = "/insertMemberPayInfo.do")
	@ResponseBody
	public MessageResponse insertMemberPayInfo(String jsons) throws Exception {
		MemberPayInfo obj = JSON.parseObject(jsons, MemberPayInfo.class);
		return this.memberPayInfoService
				.insertMemberPayInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateMemberPayInfo.do")
	@ResponseBody
	public MessageResponse updateMemberPayInfo(String jsons) throws Exception {
		MemberPayInfo obj = JSON.parseObject(jsons, MemberPayInfo.class);
		return this.memberPayInfoService
				.updateMemberPayInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectMemberPayInfoByPrimaryKey.do")
	@ResponseBody
	public SingleResult<MemberPayInfo> selectMemberPayInfoByPrimaryKey(String id)
			throws Exception {
		return this.memberPayInfoService.selectMemberPayInfoByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteMemberPayInfoByMemberPayInfoId.do")
	@ResponseBody
	public MessageResponse deleteMemberPayInfoByMemberPayInfoId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.memberPayInfoService.deleteMemberPayInfoByMemberPayInfoId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/updateAudit.do")
	@ResponseBody
	public MessageResponse updateAudit(String jsons) throws Exception {
		MemberPayInfo obj = JSON.parseObject(jsons, MemberPayInfo.class);
		return this.memberPayInfoService.updateAudit(obj, this.getCurUserProp());
	}
	@RequestMapping(value = "/selectAnaysePayMentByMonth.do")
	@ResponseBody
	List<Map<String,Object>> selectAnaysePayMentByMonth( String year,String deptId,String chargingItemId) throws Exception{
		deptId= this.getCurUserProp().getCorpId();
		return this.memberPayInfoService.selectAnaysePayMentByMonth(year, deptId, chargingItemId);
	}
	

	/**
	 * 判断费用没有收取时是否重复收
	 * 
	 * 已引用（会费收取时判断）
	 * @param memberCode 企业编号
	 * @param chargingItemId 收费项目编号
	 * @param status 审核状态
	 * @return MessageResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/isExitPayInfo.do")
	@ResponseBody
	public MessageResponse isExitPayInfo( String memberCode,String chargingItemId,String status) throws Exception{
		return this.memberPayInfoService.isExitPayInfo(memberCode, chargingItemId, status);
	}
}
