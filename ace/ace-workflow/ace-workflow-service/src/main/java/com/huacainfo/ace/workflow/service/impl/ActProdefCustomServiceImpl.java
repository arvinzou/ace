package com.huacainfo.ace.workflow.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.huacainfo.ace.workflow.dao.ActProdefCustomDao;
import com.huacainfo.ace.workflow.model.ActProdefCustom;
import com.huacainfo.ace.workflow.service.ActProdefCustomService;
import com.huacainfo.ace.workflow.vo.ActProdefCustomQVo;
import com.huacainfo.ace.workflow.vo.ActProdefCustomVo;

@Service("actProdefCustomService")
public class ActProdefCustomServiceImpl implements ActProdefCustomService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActProdefCustomDao actProdefCustomDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ActProdefCustomVo> findActProdefCustomList(
			ActProdefCustomQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<ActProdefCustomVo> rst = new PageResult<ActProdefCustomVo>();
		List<ActProdefCustomVo> list = this.actProdefCustomDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.actProdefCustomDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertActProdefCustom(ActProdefCustom o,
			UserProp userProp) throws Exception {
		
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "流程编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "所属企业编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategoryId())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态（1启用0禁用）不能为空！");
		}
		
		int temp = this.actProdefCustomDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "工作流分配名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actProdefCustomDao.insert(o);
		this.dataBaseLogService.log("添加工作流分配", "工作流分配", "", o.getId(),
				o.getId(), userProp);
		return new MessageResponse(0, "添加工作流分配完成！");
	}

	public MessageResponse updateActProdefCustom(ActProdefCustom o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "流程编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getDeptId())) {
			return new MessageResponse(1, "所属企业编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategoryId())) {
			return new MessageResponse(1, "类别不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态（1启用0禁用）不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.actProdefCustomDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更工作流分配", "工作流分配", "", o.getId(),
				o.getId(), userProp);
		return new MessageResponse(0, "变更工作流分配完成！");
	}

	public SingleResult<ActProdefCustom> selectActProdefCustomByPrimaryKey(
			String id) throws Exception {
		SingleResult<ActProdefCustom> rst = new SingleResult<ActProdefCustom>();
		rst.setValue(this.actProdefCustomDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteActProdefCustomByActProdefCustomId(String id,
			UserProp userProp) throws Exception {
		this.actProdefCustomDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除工作流分配", "工作流分配", String.valueOf(id),
				String.valueOf(id), "工作流分配", userProp);
		return new MessageResponse(0, "工作流分配删除完成！");
	}

	public List<Tree> selectTreeList() throws Exception {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.actProdefCustomDao.selectTreeList());
		return commonTreeUtils.getTreeList("0");
	}
	
	public Map<String, Object> selectGridList(Map<String, Object> params)throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, String>> list = this.actProdefCustomDao.selectGridList(params);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}

}
