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
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.dao.FuPinDao;
import com.huacainfo.ace.uf.model.FuPin;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.FuPinService;
import com.huacainfo.ace.uf.vo.FuPinVo;
import com.huacainfo.ace.uf.vo.FuPinQVo;
@Service("fuPinService")
public class FuPinServiceImpl implements FuPinService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuPinDao fuPinDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<FuPinVo> findFuPinList(FuPinQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<FuPinVo> rst = new PageResult<FuPinVo>();
		List<FuPinVo> list = this.fuPinDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.fuPinDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	@Override
	public MessageResponse insertFuPin(FuPin o, UserProp userProp) throws Exception {
		o.setId(String.valueOf(System.currentTimeMillis()));
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "扶贫名称名称不能为空");
		}
		if (CommonUtils.isBlank(o.getAddress())){
			return new MessageResponse(1, "扶贫地址不能为空");
		}

		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "扶贫进程不能为空");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "扶贫内容不能为空");
		}
		int temp = this.fuPinDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "推文名称重复！");
		}
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.fuPinDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}

	@Override
	public MessageResponse updateFuPin(FuPin o, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "扶贫名称名称不能为空");
		}
		if (CommonUtils.isBlank(o.getAddress())){
			return new MessageResponse(1, "扶贫地址不能为空");
		}

		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "扶贫进程不能为空");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "扶贫内容不能为空");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.fuPinDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");
	}

	@Override
	public SingleResult<FuPinVo> selectFuPinByPrimaryKey(String id) throws Exception {
		SingleResult<FuPinVo> rst = new SingleResult<FuPinVo>();
		rst.setValue(this.fuPinDao.selectByPrimaryKey(id));
		return rst;
	}

	@Override
	public MessageResponse deleteFuPinByFuPinId(String id,
			UserProp userProp) throws Exception {
		this.fuPinDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}
