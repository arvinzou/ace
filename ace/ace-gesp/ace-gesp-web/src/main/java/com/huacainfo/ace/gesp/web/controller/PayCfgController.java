package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.service.MemberPayInfoService;
import com.huacainfo.ace.gesp.vo.PayCfgQVo;
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
import com.huacainfo.ace.gesp.model.PayCfg;
import com.huacainfo.ace.gesp.service.PayCfgService;
import com.huacainfo.ace.gesp.vo.PayCfgVo;

@Controller
@RequestMapping("/payCfg")
public class PayCfgController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayCfgService payCfgService;
	@Autowired
	private MemberPayInfoService memberPayInfoService;

	@RequestMapping(value = "/findPayCfgList.do")
	@ResponseBody
	public PageResult<PayCfgVo> findPayCfgList(PayCfgQVo condition,
			PageParamNoChangeSord page) throws Exception {
		if(CommonUtils.isBlank(condition.getMemberCode())){
			condition.setMemberCode(this.getCurUserProp().getCorpId());
		}
		String code = condition.getMemberCode();
		if(code.length()<5){
			condition.setMemberCode("5");
		}
		PageResult<PayCfgVo> rst = this.payCfgService
				.findPayCfgList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPayCfg.do")
	@ResponseBody
	public MessageResponse insertPayCfg(String jsons) throws Exception {
		PayCfg obj = JSON.parseObject(jsons, PayCfg.class);
		return this.payCfgService
				.insertPayCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePayCfg.do")
	@ResponseBody
	public MessageResponse updatePayCfg(String jsons) throws Exception {
		PayCfg obj = JSON.parseObject(jsons, PayCfg.class);
		return this.payCfgService
				.updatePayCfg(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPayCfgByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PayCfg> selectPayCfgByPrimaryKey(String id)
			throws Exception {
		return this.payCfgService.selectPayCfgByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePayCfgByPayCfgId.do")
	@ResponseBody
	public MessageResponse deletePayCfgByPayCfgId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.payCfgService.deletePayCfgByPayCfgId(id,
				this.getCurUserProp());
	}
	
	
	@RequestMapping(value = "/selectPayCfgList.do")
	@ResponseBody
	public List<PayCfg> selectPayCfgList(String memberCode)
			throws Exception {
		if(CommonUtils.isBlank(memberCode)){
			memberCode=this.getCurUserProp().getCorpId();
		}
		return this.payCfgService.selectPayCfgList(memberCode);
	}
	@RequestMapping(value = "/getPayInfo.do")
	@ResponseBody
	public SingleResult<Map<String, Object>> getPayInfo(String memberCode,
			String memberLevelId,String chargingItemId)
			throws Exception {
		return this.payCfgService.getPayInfo(memberCode, memberLevelId, chargingItemId,this.getCurUserProp().getCorpId());
	}

	/**
	 * 校验收费配置是否配置完成
	 * 
	 * @param memberCode
	 * @param chargingItemId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectCount.do")
	@ResponseBody
	public MessageResponse selectCount(String memberCode,String chargingItemId)throws Exception {
		return this.payCfgService.selectCount(this.getCurUserProp().getCorpId(), chargingItemId);
	}
	
}
