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
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.dao.OrganizationSubDao;
import com.huacainfo.ace.uf.model.OrganizationSub;
import com.huacainfo.ace.uf.service.OrganizationSubService;
import com.huacainfo.ace.uf.vo.OrganizationSubQVo;
import com.huacainfo.ace.uf.vo.OrganizationSubVo;
@Service("organizationSubService")
public class OrganizationSubServiceImpl implements OrganizationSubService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationSubDao organizationSubDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<OrganizationSubVo> findOrganizationSubList(OrganizationSubQVo condition, int start, int limit,
			String orderBy) throws Exception {
		PageResult<OrganizationSubVo> rst = new PageResult<OrganizationSubVo>();
		List<OrganizationSubVo> list = this.organizationSubDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.organizationSubDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertOrganizationSub(OrganizationSub o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setAreaCode(userProp.getAreaCode());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getOrganizationId())) {
			return new MessageResponse(1, "资源编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getUrl())) {
			return new MessageResponse(1, "地址不能为空！");
		}
		int temp = this.organizationSubDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "社会资源图片名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.organizationSubDao.insert(o);
		this.dataBaseLogService.log("添加社会资源图片", "社会资源图片", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加社会资源图片完成！");
	}

	public MessageResponse updateOrganizationSub(OrganizationSub o, UserProp userProp) throws Exception {
		o.setAreaCode(userProp.getAreaCode());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getOrganizationId())) {
			return new MessageResponse(1, "资源编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属辖区不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getUrl())) {
			return new MessageResponse(1, "地址不能为空！");
		}
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.organizationSubDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更社会资源图片", "社会资源图片", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更社会资源图片完成！");
	}

	public SingleResult<OrganizationSubVo> selectOrganizationSubByPrimaryKey(String id) throws Exception {
		SingleResult<OrganizationSubVo> rst = new SingleResult<OrganizationSubVo>();
		rst.setValue(this.organizationSubDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteOrganizationSubByOrganizationSubId(String id, UserProp userProp) throws Exception {
		this.organizationSubDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除社会资源图片", "社会资源图片", String.valueOf(id), String.valueOf(id), "社会资源图片", userProp);
		return new MessageResponse(0, "社会资源图片删除完成！");
	}
}
