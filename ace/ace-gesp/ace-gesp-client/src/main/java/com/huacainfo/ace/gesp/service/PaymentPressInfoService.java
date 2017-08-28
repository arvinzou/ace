package com.huacainfo.ace.gesp.service;

import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.gesp.model.PaymentPressInfo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoQVo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoVo;

public interface PaymentPressInfoService {
	
	public abstract PageResult<PaymentPressInfoVo> findPaymentPressInfoList(PaymentPressInfoQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPaymentPressInfo(PaymentPressInfo obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePaymentPressInfo(PaymentPressInfo obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PaymentPressInfo> selectPaymentPressInfoByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePaymentPressInfoByPaymentPressInfoId(String id,UserProp userProp) throws Exception;
	public abstract ListResult<Map<String,Object>> selectListByDeptId(String deptId) throws Exception ;
	
}
