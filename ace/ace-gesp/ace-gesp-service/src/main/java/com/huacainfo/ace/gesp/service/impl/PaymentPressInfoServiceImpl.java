package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.dao.PaymentPressInfoDao;
import com.huacainfo.ace.gesp.model.PaymentPressInfo;
import com.huacainfo.ace.gesp.service.PaymentPressInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoQVo;
import com.huacainfo.ace.gesp.vo.PaymentPressInfoVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("paymentPressInfoService")
public class PaymentPressInfoServiceImpl implements PaymentPressInfoService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentPressInfoDao paymentPressInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PaymentPressInfoVo> findPaymentPressInfoList(
			PaymentPressInfoQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PaymentPressInfoVo> rst = new PageResult<PaymentPressInfoVo>();
		List<PaymentPressInfoVo> list = this.paymentPressInfoDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.paymentPressInfoDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPaymentPressInfo(PaymentPressInfo o,
			UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setDeptId(userProp.getCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "ID不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "协会编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "缴费项目不能为空！");
		}
		if (CommonUtils.isBlank(o.getEndDate())) {
			return new MessageResponse(1, "收缴截止时间不能为空！");
		}
		
		int temp = this.paymentPressInfoDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "催缴记录名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("0");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.paymentPressInfoDao.insert(o);
		this.dataBaseLogService.log("添加催缴记录", "催缴记录", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加催缴记录完成！");
	}

	public MessageResponse updatePaymentPressInfo(PaymentPressInfo o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "ID不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "协会编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "催缴记录ID不能为空！");
		}
		if (CommonUtils.isBlank(o.getEndDate())) {
			return new MessageResponse(1, "收缴截止时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateDate())) {
			return new MessageResponse(1, "创建时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateUserId())) {
			return new MessageResponse(1, "创建人编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateUserName())) {
			return new MessageResponse(1, "创建人不能为空！");
		}

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.paymentPressInfoDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更催缴记录", "催缴记录", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更催缴记录完成！");
	}

	public SingleResult<PaymentPressInfo> selectPaymentPressInfoByPrimaryKey(
			String id) throws Exception {
		SingleResult<PaymentPressInfo> rst = new SingleResult<PaymentPressInfo>();
		rst.setValue(this.paymentPressInfoDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePaymentPressInfoByPaymentPressInfoId(
			String id, UserProp userProp) throws Exception {
		this.paymentPressInfoDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除催缴记录", "催缴记录", String.valueOf(id),
				String.valueOf(id), "催缴记录", userProp);
		return new MessageResponse(0, "催缴记录删除完成！");
	}

	public ListResult<Map<String, Object>> selectListByDeptId(String deptId)
			throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		rst.setValue(this.paymentPressInfoDao.selectListByDeptId(deptId));
		return rst;
	}

}
