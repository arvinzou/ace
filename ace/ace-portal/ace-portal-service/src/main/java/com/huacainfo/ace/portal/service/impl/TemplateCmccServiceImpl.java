package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonUtils;

import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.portal.dao.TemplateCmccMapper;
import com.huacainfo.ace.portal.model.TemplateCmcc;
import com.huacainfo.ace.portal.service.TemplateCmccService;
import com.huacainfo.ace.portal.vo.TemplateCmccQVo;
import com.huacainfo.ace.portal.vo.TemplateCmccVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("templateCmccService")
public class TemplateCmccServiceImpl implements TemplateCmccService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateCmccMapper templateCmccMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public PageResult<TemplateCmccVo> findTemplateCmccList(TemplateCmccQVo condition, int start,
														   int limit, String orderBy) throws Exception {
		PageResult<TemplateCmccVo> rst = new PageResult<TemplateCmccVo>();
		List<TemplateCmccVo> list = this.templateCmccMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.templateCmccMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertTemplateCmcc(TemplateCmcc o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getTemplateCmccId())) {
			return new MessageResponse(1, "模板编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "模板不能为空！");
		}
		int temp = this.templateCmccMapper.isExitByName(o.getName());
		if (temp > 0) {
			return new MessageResponse(1, "已存在此身份证的数据！");
		}
		o.setCreateTime(new Date());
		this.templateCmccMapper.insert(o);
		this.dataBaseLogService.log("添加模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加模板完成！");
	}

	public MessageResponse updateTemplateCmcc(TemplateCmcc o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getTemplateCmccId())) {
			return new MessageResponse(1, "模板编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getContent())) {
			return new MessageResponse(1, "模板不能为空！");
		}
		int temp = this.templateCmccMapper.isExitByName(o.getName());
		if (temp > 0) {
			return new MessageResponse(1, "已存在此身份证的数据！");
		}
		o.setCreateTime(new Date());
		
		this.templateCmccMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更模板", "模板", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更模板完成！");
	}



	public MessageResponse deleteTemplateCmccByTemplateCmccId(String id,
			UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.templateCmccMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除模板", "模板", String.valueOf(id), String.valueOf(id),
				"模板", userProp);
		return rst;
	}

}
