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
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.workflow.dao.ActDeModelDao;
import com.huacainfo.ace.workflow.dao.ActFormDefDao;
import com.huacainfo.ace.workflow.model.ActDeModel;
import com.huacainfo.ace.workflow.model.ActFormDef;
import com.huacainfo.ace.workflow.service.ActFormDefService;
import com.huacainfo.ace.workflow.vo.ActFormDefQVo;
import com.huacainfo.ace.workflow.vo.ActFormDefVo;

@Service("actFormDefService")
public class ActFormDefServiceImpl implements ActFormDefService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActFormDefDao actFormDefDao;
	@Autowired
	private ActDeModelDao actDeModelDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ActFormDefVo> findActFormDefList(ActFormDefQVo condition,
			int start, int limit, String orderBy) throws Exception {
		PageResult<ActFormDefVo> rst = new PageResult<ActFormDefVo>();
		List<ActFormDefVo> list = this.actFormDefDao.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.actFormDefDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertActFormDef(ActFormDef o, UserProp userProp)
			throws Exception {
		//o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		int temp = this.actFormDefDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "表单名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getCorpName());
		o.setCreateUserId(userProp.getUserId());
		this.actFormDefDao.insert(o);
		this.saveOrUpdateActDeModel(o,userProp.getUserId());
		this.dataBaseLogService.log("添加表单", "表单", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "添加表单完成！");
	}

	public MessageResponse updateActFormDef(ActFormDef o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "序号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.actFormDefDao.updateByPrimaryKey(o);
		this.saveOrUpdateActDeModel(o,userProp.getUserId());
		this.dataBaseLogService.log("变更表单", "表单", "", o.getName(), o.getName(),
				userProp);
		return new MessageResponse(0, "变更表单完成！");
	}

	public SingleResult<ActFormDef> selectActFormDefByPrimaryKey(String id)
			throws Exception {
		SingleResult<ActFormDef> rst = new SingleResult<ActFormDef>();
		rst.setValue(this.actFormDefDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteActFormDefByActFormDefId(String id,
			UserProp userProp) throws Exception {
		this.actFormDefDao.deleteByPrimaryKey(id);
		this.actDeModelDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除表单", "表单", String.valueOf(id),
				String.valueOf(id), "表单", userProp);
		return new MessageResponse(0, "表单删除完成！");
	}

	public ListResult<Map<String, Object>> selectListByDeptId(String deptId)
			throws Exception {
		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
		rst.setValue(this.actFormDefDao.selectListByDeptId(deptId));
		return rst;
	}
	
	private void saveOrUpdateActDeModel(ActFormDef o,String userId){
		ActDeModel obj=new ActDeModel();
		obj.setId(new Date().getTime());
		obj.setModelKey(o.getId());
		obj.setModelType(2);
		obj.setName(o.getName());
		obj.setDescription(o.getDiscribtion());
		obj.setCreated(new Date());
		obj.setLastUpdated(new Date());
		obj.setCreatedBy(userId);
		obj.setLastUpdatedBy(userId);
		obj.setVersion(1);
		if(this.actDeModelDao.isExit(obj)>0){
			this.actDeModelDao.updateByPrimaryKey(obj);
		}else{
			//this.actDeModelDao.insert(obj);
		}
	}
	public  Map<String, Object> selectFormDefList(
			Map<String, String> params){
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.actFormDefDao
				.selectFormDefList(params);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}
}
