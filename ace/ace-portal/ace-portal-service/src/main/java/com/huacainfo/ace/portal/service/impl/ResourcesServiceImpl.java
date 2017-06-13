package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.security.spring.SecurityLoadResouceDefine;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.portal.dao.ResourcesMapper;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.vo.ResourcesVo;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService,
		SecurityLoadResouceDefine {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ResourcesMapper resourcesMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<ResourcesVo> findResourcesList(Resources condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ResourcesVo> rst = new PageResult<ResourcesVo>();
		List<ResourcesVo> list = this.resourcesMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.resourcesMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertResources(Resources o, UserProp userProp)
			throws Exception {
		StringBuffer msg=new StringBuffer("添加资源完成");
		if (CommonUtils.isBlank(o.getResourcesId())) {
			return new MessageResponse(1, "编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getParentResourcesId())) {
			return new MessageResponse(1, "父编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getResourcesName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getResourcesType())) {
			return new MessageResponse(1, "类型不能为空！");
		}
		o.setSyid(userProp.getActiveSyId());
		int temp = this.resourcesMapper.isExitByName(o.getResourcesName());
		if (temp > 0) {
			msg.append(",但资源名称重复");
			//return new MessageResponse(1, "资源名称重复！");
		}
		msg.append("!");
		o.setCreateTime(new Date());
		o.setStauts("1");
		o.setCreateUserId(userProp.getUserId());
		this.resourcesMapper.insert(o);
		this.dataBaseLogService.log("添加资源", "资源", "", o.getResourcesName(),
				o.getResourcesName(), userProp);
		return new MessageResponse(0, "添加资源完成！");
	}

	public MessageResponse updateResources(Resources o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getResourcesId())) {
			return new MessageResponse(1, "编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getParentResourcesId())) {
			return new MessageResponse(1, "父编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getResourcesName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getResourcesType())) {
			return new MessageResponse(1, "类型不能为空！");
		}
		o.setSyid(userProp.getActiveSyId());
		this.resourcesMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更资源", "资源", "", o.getResourcesName(),
				o.getResourcesName(), userProp);
		return new MessageResponse(0, "变更资源完成！");
	}

	public SingleResult<Resources> selectResourcesByPrimaryKey(String id) throws Exception {
		SingleResult<Resources> rst = new SingleResult<Resources>();
		rst.setValue(this.resourcesMapper.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteResourcesByResourcesId(String id,
			UserProp userProp) throws Exception {
		this.resourcesMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除资源", "资源", String.valueOf(id),
				String.valueOf(id), "资源", userProp);
		return new MessageResponse(0, "资源删除完成！");
	}

	public List<Tree> selectResourcesTreeList() throws Exception {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.resourcesMapper.selectResourcesTreeList());
		return commonTreeUtils.getTreeList("0");
	}

	@Override
	public List<Map<String, String>> loadResourceDefine() {
		return this.resourcesMapper.loadResourceDefine();
	}
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
		int i = 1;
		for (Map<String, Object> row : list) {
			Resources o = new Resources();
			CommonBeanUtils.copyMap2Bean(o,row);
			o.setCreateTime(new Date());
			o.setCreateUserId(userProp.getUserId());
			o.setStauts("1");
			if(CommonUtils.isBlank(o.getSyid())){
				o.setSyid(userProp.getActiveSyId());
			}
			this.logger.info(o.toString());
			if (CommonUtils.isBlank(o.getResourcesId())) {
				return new MessageResponse(1,"行"+i+ ",编号不能为空！");
			}
			if (CommonUtils.isBlank(o.getParentResourcesId())) {
				return new MessageResponse(1, "行"+i+ ",父编号不能为空！");
			}
			if (CommonUtils.isBlank(o.getResourcesName())) {
				return new MessageResponse(1, "行"+i+ ",名称不能为空！");
			}
			if (CommonUtils.isBlank(o.getResourcesType())) {
				return new MessageResponse(1, "行"+i+ ",类型不能为空！");
			}
			int t = resourcesMapper.isExit(o);
			if (t > 0) {
				this.resourcesMapper.updateByPrimaryKey(o);

			} else {
				this.resourcesMapper.insert(o);
			}
			i++;
		}
		this.dataBaseLogService.log("资源导入", "资源", "", "rs.xls",
				"rs.xls", userProp);
		return new MessageResponse(0, "资源导入完成！");
	}

	public MessageResponse updateSequence(List<Map<String, Object>> list, UserProp userProp)
			throws Exception{
		for(Map<String, Object> o:list){
			this.resourcesMapper.updateSequence((int)o.get("sequence"),(String)o.get("resourcesId"));
		}
		this.dataBaseLogService.log("排序", "资源", "", "",
				"", userProp);
		return new MessageResponse(0, "完成！");
	}
}
