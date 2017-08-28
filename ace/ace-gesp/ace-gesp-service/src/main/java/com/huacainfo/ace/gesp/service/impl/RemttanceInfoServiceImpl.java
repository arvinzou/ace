package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.dao.RemttanceInfoDao;
import com.huacainfo.ace.gesp.service.RemttanceInfoService;
import com.huacainfo.ace.gesp.vo.RemttanceInfoQVo;
import com.huacainfo.ace.gesp.vo.RemttanceInfoVo;
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
import com.huacainfo.ace.gesp.model.RemttanceInfo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.FilesService;

@Service("remttanceInfoService")
public class RemttanceInfoServiceImpl implements RemttanceInfoService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RemttanceInfoDao remttanceInfoDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private FilesService filesService;
	public PageResult<RemttanceInfoVo> findRemttanceInfoList(
            RemttanceInfoQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<RemttanceInfoVo> rst = new PageResult<RemttanceInfoVo>();
		List<RemttanceInfoVo> list = this.remttanceInfoDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.remttanceInfoDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertRemttanceInfo(RemttanceInfo o,
			UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setMemberCode(userProp.getCorpId());
		o.setDeptId(userProp.getParentCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "ID不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "所属协会编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "服务事项不能为空！");
		}
		if (CommonUtils.isBlank(o.getBankName())) {
			return new MessageResponse(1, "汇款银行名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getBankAcc())) {
			return new MessageResponse(1, "汇款银行账号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "汇款人姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getAmount())) {
			return new MessageResponse(1, "汇款金额（元）不能为空！");
		}
		if (CommonUtils.isBlank(o.getRemDate())) {
			return new MessageResponse(1, "汇款时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getMobile())) {
			return new MessageResponse(1, "联系人手机不能为空！");
		}
		if (CommonUtils.isBlank(o.getFileAddr())) {
			return new MessageResponse(1, "凭证不能为空！");
		}
		
		int temp = this.remttanceInfoDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "汇款信息名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("0");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.remttanceInfoDao.insert(o);
		filesService.updateFiles(o.getFileAddr(), userProp);
		this.dataBaseLogService.log("添加汇款信息", "汇款信息", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加汇款信息完成！");
	}

	public MessageResponse updateRemttanceInfo(RemttanceInfo o,
			UserProp userProp) throws Exception {
		o.setMemberCode(userProp.getCorpId());
		o.setDeptId(userProp.getParentCorpId());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "ID不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "所属协会编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getMemberCode())) {
			return new MessageResponse(1, "会员编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getChargingItemId())) {
			return new MessageResponse(1, "服务事项不能为空！");
		}
		if (CommonUtils.isBlank(o.getBankName())) {
			return new MessageResponse(1, "汇款银行名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getBankAcc())) {
			return new MessageResponse(1, "汇款银行账号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "汇款人姓名不能为空！");
		}
		if (CommonUtils.isBlank(o.getAmount())) {
			return new MessageResponse(1, "汇款金额（元）不能为空！");
		}
		if (CommonUtils.isBlank(o.getRemDate())) {
			return new MessageResponse(1, "汇款时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getMobile())) {
			return new MessageResponse(1, "联系人手机不能为空！");
		}
		if (CommonUtils.isBlank(o.getFileAddr())) {
			return new MessageResponse(1, "凭证不能为空！");
		}
		

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.remttanceInfoDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更汇款信息", "汇款信息", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更汇款信息完成！");
	}

	public SingleResult<RemttanceInfo> selectRemttanceInfoByPrimaryKey(String id)
			throws Exception {
		SingleResult<RemttanceInfo> rst = new SingleResult<RemttanceInfo>();
		rst.setValue(this.remttanceInfoDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteRemttanceInfoByRemttanceInfoId(String id,
			UserProp userProp) throws Exception {
		this.remttanceInfoDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除汇款信息", "汇款信息", String.valueOf(id),
				String.valueOf(id), "汇款信息", userProp);
		return new MessageResponse(0, "汇款信息删除完成！");
	}

	public ListResult<Map<String, Object>> selectListByDeptId(String deptId)
			throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		rst.setValue(this.remttanceInfoDao.selectListByDeptId(deptId));
		return rst;
	}

}
