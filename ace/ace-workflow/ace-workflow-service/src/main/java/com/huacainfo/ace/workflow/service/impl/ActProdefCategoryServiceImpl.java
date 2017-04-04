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
import com.huacainfo.ace.workflow.dao.ActProdefCategoryDao;
import com.huacainfo.ace.workflow.model.ActProdefCategory;
import com.huacainfo.ace.workflow.service.ActProdefCategoryService;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCategoryVo;

@Service("actProdefCategoryService")
public class ActProdefCategoryServiceImpl implements ActProdefCategoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActProdefCategoryDao actProdefCategoryDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ActProdefCategoryVo> findActProdefCategoryList(
			ActProdefCategoryQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<ActProdefCategoryVo> rst = new PageResult<ActProdefCategoryVo>();
		List<ActProdefCategoryVo> list = this.actProdefCategoryDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.actProdefCategoryDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertActProdefCategory(ActProdefCategory o,
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
		int temp = this.actProdefCategoryDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "表单类型名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actProdefCategoryDao.insert(o);
		this.dataBaseLogService.log("添加表单类型", "表单类型", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "添加表单类型完成！");
	}

	public MessageResponse updateActProdefCategory(ActProdefCategory o,
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
		this.actProdefCategoryDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更表单类型", "表单类型", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "变更表单类型完成！");
	}

	public SingleResult<ActProdefCategory> selectActProdefCategoryByPrimaryKey(
			String id) throws Exception {
		SingleResult<ActProdefCategory> rst = new SingleResult<ActProdefCategory>();
		rst.setValue(this.actProdefCategoryDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteActProdefCategoryByActProdefCategoryId(String id,
			UserProp userProp) throws Exception {
		this.actProdefCategoryDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除表单类型", "表单类型", String.valueOf(id),
				String.valueOf(id), "表单类型", userProp);
		return new MessageResponse(0, "表单类型删除完成！");
	}

	public List<Tree> selectTreeList() throws Exception {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.actProdefCategoryDao.selectTreeList());
		return commonTreeUtils.getTreeList("0");
	}

}
