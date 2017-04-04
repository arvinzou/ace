package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.DictCategoryMapper;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.DictCategoryService;

@Service("dictCategoryService")
public class DictCategoryServiceImpl implements DictCategoryService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DictCategoryMapper dictCategoryMapper;

	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<DictCategory> findDictCategoryList(DictCategory condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<DictCategory> rst = new PageResult<DictCategory>();
		List<DictCategory> list = this.dictCategoryMapper.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.dictCategoryMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertDictCategory(DictCategory o,
			UserProp userProp) throws Exception {
		o.setSyid(userProp.getActiveSyId());
		if (CommonUtils.isBlank(o.getName())) {

			return new MessageResponse(1, "名称不能为空！");
		}
		o.setCreateTime(new Date());
		int temp = this.dictCategoryMapper.isExitByName(o.getName());
		if (temp > 0) {
			return new MessageResponse(1, "名称已存在！");
		}
		this.dictCategoryMapper.insert(o);
		this.dataBaseLogService.log("添加字典类型", "字典类型", "",
				o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加字典类型完成！");
	}

	public MessageResponse updateDictCategory(DictCategory o,
			UserProp userProp) throws Exception {
		o.setSyid(userProp.getActiveSyId());
		if (CommonUtils.isBlank(o.getName())) {

			return new MessageResponse(1, "名称不能为空！");
		}
		this.dictCategoryMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更字典类型", "字典类型", "",
				o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "字典类型变更完成！");
	}

	public SingleResult<DictCategory> selectDictCategoryByPrimaryKey(String categoryId)
			throws Exception {
		SingleResult<DictCategory> rst = new SingleResult<DictCategory>();
		rst.setValue(this.dictCategoryMapper.selectByPrimaryKey(categoryId));
		return rst;
	}

	public MessageResponse deleteDictCategoryByDictCategoryId(
			String id, UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.dictCategoryMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除字典类型", "字典类型", id,
				id, "字典类型", userProp);
		return rst;
	}

	public List<DictCategory> findDictCategoryListAll() throws Exception {
		return this.dictCategoryMapper.findListAll();
	}

}
