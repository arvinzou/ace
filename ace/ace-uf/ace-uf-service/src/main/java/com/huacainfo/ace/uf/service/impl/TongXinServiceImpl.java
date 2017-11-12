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
import com.huacainfo.ace.uf.dao.TongXinDao;
import com.huacainfo.ace.uf.model.TongXin;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.TongXinService;
import com.huacainfo.ace.uf.vo.TongXinVo;
import com.huacainfo.ace.uf.vo.TongXinQVo;
@Service("tongXinService")
public class TongXinServiceImpl implements TongXinService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TongXinDao tongXinDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<TongXinVo> findTongXinList(TongXinQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TongXinVo> rst = new PageResult<TongXinVo>();
		List<TongXinVo> list = this.tongXinDao.findList(condition, start, start + limit, orderBy);

		for(TongXinVo tx:list){
			this.logger.info(tx.toString());
		}
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.tongXinDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}


	@Override
	public MessageResponse insertTongXin(TongXin o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "工程名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getAddress())) {
			return new MessageResponse(1, "工程地址不能为空！");
		}
		if (CommonUtils.isBlank(o.getLinkman())) {
			return new MessageResponse(1, "联系人不能为空！");
		}
		if (CommonUtils.isBlank(o.getTel())) {
			return new MessageResponse(1, "联系电话不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "工程内容不能为空！");
		}
		if (CommonUtils.isBlank(o.getType())) {
			return new MessageResponse(1, "工程分类不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateTime())) {
			return new MessageResponse(1, "创建时间不能为空！");
		}
		int temp = this.tongXinDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "推文名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.tongXinDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}


	@Override
	public MessageResponse updateTongXin(TongXin o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "工程名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getAddress())) {
			return new MessageResponse(1, "工程地址不能为空！");
		}
		if (CommonUtils.isBlank(o.getLinkman())) {
			return new MessageResponse(1, "联系人不能为空！");
		}
		if (CommonUtils.isBlank(o.getTel())) {
			return new MessageResponse(1, "联系电话不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "工程内容不能为空！");
		}
		if (CommonUtils.isBlank(o.getType())) {
			return new MessageResponse(1, "工程分类不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateTime())) {
			return new MessageResponse(1, "创建时间不能为空！");
		}
		int temp = this.tongXinDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "推文名称重复！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.tongXinDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");

	}



	@Override
	public SingleResult<TongXinVo> selectTongXinByPrimaryKey(String id) throws Exception {
		SingleResult<TongXinVo> rst = new SingleResult<TongXinVo>();
		rst.setValue(this.tongXinDao.selectByPrimaryKey(id));
		return rst;
	}


	@Override
	public MessageResponse deleteTongXinByTongXinId(String id,
			UserProp userProp) throws Exception {
		this.tongXinDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}
