package com.huacainfo.ace.gesp.web.controller;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.gesp.model.PaymentPressInfo;
import com.huacainfo.ace.gesp.service.PaymentPressInfoService;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoQVo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoVo;
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
@RequestMapping("/paymentPressInfo")
public class PaymentPressInfoController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentPressInfoService paymentPressInfoService;

	@RequestMapping(value = "/findPaymentPressInfoList.do")
	@ResponseBody
	public PageResult<PaymentPressInfoVo> findPaymentPressInfoList(PaymentPressInfoQVo condition,
                                                                   PageParamNoChangeSord page) throws Exception {
		PageResult<PaymentPressInfoVo> rst = this.paymentPressInfoService
				.findPaymentPressInfoList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertPaymentPressInfo.do")
	@ResponseBody
	public MessageResponse insertPaymentPressInfo(String jsons) throws Exception {
		PaymentPressInfo obj = JSON.parseObject(jsons, PaymentPressInfo.class);
		return this.paymentPressInfoService
				.insertPaymentPressInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updatePaymentPressInfo.do")
	@ResponseBody
	public MessageResponse updatePaymentPressInfo(String jsons) throws Exception {
		PaymentPressInfo obj = JSON.parseObject(jsons, PaymentPressInfo.class);
		return this.paymentPressInfoService
				.updatePaymentPressInfo(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectPaymentPressInfoByPrimaryKey.do")
	@ResponseBody
	public SingleResult<PaymentPressInfo> selectPaymentPressInfoByPrimaryKey(String id)
			throws Exception {
		return this.paymentPressInfoService.selectPaymentPressInfoByPrimaryKey(id);
	}

	@RequestMapping(value = "/deletePaymentPressInfoByPaymentPressInfoId.do")
	@ResponseBody
	public MessageResponse deletePaymentPressInfoByPaymentPressInfoId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.paymentPressInfoService.deletePaymentPressInfoByPaymentPressInfoId(id,
				this.getCurUserProp());
	}
	
	@RequestMapping(value = "/selectListByDeptId.do")
	@ResponseBody
	public List<Map<String,Object>> selectListByDeptId(String deptId)
			throws Exception {
		if (CommonUtils.isBlank(deptId)) {
			deptId=this.getCurUserProp().getCorpId();
		}
		return this.paymentPressInfoService.selectListByDeptId(deptId).getValue();
	}
}
