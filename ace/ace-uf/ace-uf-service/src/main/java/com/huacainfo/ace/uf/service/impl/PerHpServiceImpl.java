package com.huacainfo.ace.uf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.PerHpDao;
import com.huacainfo.ace.uf.model.PerHp;
import com.huacainfo.ace.uf.service.PerHpService;
import com.huacainfo.ace.uf.vo.PerHpQVo;
import com.huacainfo.ace.uf.vo.PerHpVo;
@Service("perHpService")
public class PerHpServiceImpl implements PerHpService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerHpDao perHpDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PerHpVo> findPerHpList(PerHpQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PerHpVo> rst = new PageResult<PerHpVo>();
		List<PerHpVo> list = this.perHpDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.perHpDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPerHp(PerHp o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));

		int temp = this.perHpDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "荣誉&奖惩名称重复！");
		}
		o.setCreateDate(new Date());

		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.perHpDao.insert(o);
		this.dataBaseLogService.log("添加荣誉&奖惩", "荣誉&奖惩", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加荣誉&奖惩完成！");
	}

	public MessageResponse updatePerHp(PerHp o, UserProp userProp) throws Exception {

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.perHpDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更荣誉&奖惩", "荣誉&奖惩", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更荣誉&奖惩完成！");
	}

	public SingleResult<PerHpVo> selectPerHpByPrimaryKey(String id) throws Exception {
		SingleResult<PerHpVo> rst = new SingleResult<PerHpVo>();
		rst.setValue(this.perHpDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePerHpByPerHpId(String id, UserProp userProp) throws Exception {
		this.perHpDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除荣誉&奖惩", "荣誉&奖惩", String.valueOf(id), String.valueOf(id), "荣誉&奖惩", userProp);
		return new MessageResponse(0, "荣誉&奖惩删除完成！");
	}
}
