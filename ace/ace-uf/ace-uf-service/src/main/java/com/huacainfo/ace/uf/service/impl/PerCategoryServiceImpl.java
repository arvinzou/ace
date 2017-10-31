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
import com.huacainfo.ace.uf.dao.PerCategoryDao;
import com.huacainfo.ace.uf.model.PerCategory;
import com.huacainfo.ace.uf.service.PerCategoryService;
import com.huacainfo.ace.uf.vo.PerCategoryQVo;
import com.huacainfo.ace.uf.vo.PerCategoryVo;
@Service("perCategoryService")
public class PerCategoryServiceImpl implements PerCategoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerCategoryDao perCategoryDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PerCategoryVo> findPerCategoryList(PerCategoryQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PerCategoryVo> rst = new PageResult<PerCategoryVo>();
		List<PerCategoryVo> list = this.perCategoryDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.perCategoryDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPerCategory(PerCategory o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));

		int temp = this.perCategoryDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "人士类型名称重复！");
		}
		o.setCreateDate(new Date());

		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.perCategoryDao.insert(o);
		this.dataBaseLogService.log("添加人士类型", "人士类型", "", o.getCategoryId(), o.getCategoryId(), userProp);
		return new MessageResponse(0, "添加人士类型完成！");
	}

	public MessageResponse updatePerCategory(PerCategory o, UserProp userProp) throws Exception {

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.perCategoryDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更人士类型", "人士类型", "", o.getCategoryId(), o.getCategoryId(), userProp);
		return new MessageResponse(0, "变更人士类型完成！");
	}

	public SingleResult<PerCategoryVo> selectPerCategoryByPrimaryKey(String id) throws Exception {
		SingleResult<PerCategoryVo> rst = new SingleResult<PerCategoryVo>();
		rst.setValue(this.perCategoryDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePerCategoryByPerCategoryId(String id, UserProp userProp) throws Exception {
		this.perCategoryDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除人士类型", "人士类型", String.valueOf(id), String.valueOf(id), "人士类型", userProp);
		return new MessageResponse(0, "人士类型删除完成！");
	}
}
