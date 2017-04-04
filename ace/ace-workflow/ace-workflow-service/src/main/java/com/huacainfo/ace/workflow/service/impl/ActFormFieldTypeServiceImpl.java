package com.huacainfo.ace.workflow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.workflow.dao.ActFormFieldTypeDao;
import com.huacainfo.ace.workflow.model.ActFormFieldType;
import com.huacainfo.ace.workflow.service.ActFormFieldTypeService;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeQVo;
import com.huacainfo.ace.workflow.vo.ActFormFieldTypeVo;

@Service("actFormFieldTypeService")
public class ActFormFieldTypeServiceImpl implements ActFormFieldTypeService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormFieldTypeDao actFormFieldTypeDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ActFormFieldTypeVo> findActFormFieldTypeList(
			ActFormFieldTypeQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<ActFormFieldTypeVo> rst = new PageResult<ActFormFieldTypeVo>();
		List<ActFormFieldTypeVo> list = this.actFormFieldTypeDao.findList(
				condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.actFormFieldTypeDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertActFormFieldType(ActFormFieldType o,
			UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "组件编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "组件名称不能为空！");
		}
		
		int temp = this.actFormFieldTypeDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "组件类型名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actFormFieldTypeDao.insert(o);
		this.dataBaseLogService.log("添加组件类型", "组件类型", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加组件类型完成！");
	}

	public MessageResponse updateActFormFieldType(ActFormFieldType o,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "组件编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "组件名称不能为空！");
		}
		o.setStatus("1");
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.actFormFieldTypeDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更组件类型", "组件类型", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更组件类型完成！");
	}

	public SingleResult<ActFormFieldType> selectActFormFieldTypeByPrimaryKey(
			String id) throws Exception {
		SingleResult<ActFormFieldType> rst = new SingleResult<ActFormFieldType>();
		rst.setValue(this.actFormFieldTypeDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteActFormFieldTypeByActFormFieldTypeId(
			String id, UserProp userProp) throws Exception {
		this.actFormFieldTypeDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除组件类型", "组件类型", String.valueOf(id),
				String.valueOf(id), "组件类型", userProp);
		return new MessageResponse(0, "组件类型删除完成！");
	}

	public ListResult<Map<String, Object>> selectListByDeptId(String deptId)
			throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		rst.setValue(this.actFormFieldTypeDao.selectListByDeptId(deptId));
		return rst;
	}

}
