package com.huacainfo.ace.workflow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.workflow.dao.ActFormCategoryDao;
import com.huacainfo.ace.workflow.model.ActFormCategory;
import com.huacainfo.ace.workflow.service.ActFormCategoryService;
import com.huacainfo.ace.workflow.vo.ActFormCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActFormCategoryVo;

@Service("actFormCategoryService")
public class ActFormCategoryServiceImpl implements ActFormCategoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormCategoryDao actFormCategoryDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ActFormCategoryVo> findActFormCategoryList(
			ActFormCategoryQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<ActFormCategoryVo> rst = new PageResult<ActFormCategoryVo>();
		List<ActFormCategoryVo> list = this.actFormCategoryDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.actFormCategoryDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertActFormCategory(ActFormCategory o,
			UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getPid())) {
			return new MessageResponse(1, "父序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		int temp = this.actFormCategoryDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "表单类型名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actFormCategoryDao.insert(o);
		this.dataBaseLogService.log("添加表单类型", "表单类型", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "添加表单类型完成！");
	}

	public MessageResponse updateActFormCategory(ActFormCategory o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getPid())) {
			return new MessageResponse(1, "父序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.actFormCategoryDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更表单类型", "表单类型", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "变更表单类型完成！");
	}

	public SingleResult<ActFormCategory> selectActFormCategoryByPrimaryKey(
			String id) throws Exception {
		SingleResult<ActFormCategory> rst = new SingleResult<ActFormCategory>();
		rst.setValue(this.actFormCategoryDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteActFormCategoryByActFormCategoryId(String id,
			UserProp userProp) throws Exception {
		this.actFormCategoryDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除表单类型", "表单类型", String.valueOf(id),
				String.valueOf(id), "表单类型", userProp);
		return new MessageResponse(0, "表单类型删除完成！");
	}

	public List<Tree> selectTreeList() throws Exception {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.actFormCategoryDao.selectTreeList());
		return commonTreeUtils.getTreeList("0");
	}

}
