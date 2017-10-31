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
import com.huacainfo.ace.uf.dao.PerDeptDao;
import com.huacainfo.ace.uf.model.PerDept;
import com.huacainfo.ace.uf.service.PerDeptService;
import com.huacainfo.ace.uf.vo.PerDeptQVo;
import com.huacainfo.ace.uf.vo.PerDeptVo;
@Service("perDeptService")
public class PerDeptServiceImpl implements PerDeptService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerDeptDao perDeptDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PerDeptVo> findPerDeptList(PerDeptQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PerDeptVo> rst = new PageResult<PerDeptVo>();
		List<PerDeptVo> list = this.perDeptDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.perDeptDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPerDept(PerDept o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		// o.setId(String.valueOf(new Date().getTime()));

		int temp = this.perDeptDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "单位名称重复！");
		}
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.perDeptDao.insert(o);
		this.dataBaseLogService.log("添加单位", "单位", "", o.getDeptId(), o.getDeptId(), userProp);
		return new MessageResponse(0, "添加单位完成！");
	}

	public MessageResponse updatePerDept(PerDept o, UserProp userProp) throws Exception {

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.perDeptDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更单位", "单位", "", o.getDeptId(), o.getDeptId(), userProp);
		return new MessageResponse(0, "变更单位完成！");
	}

	public SingleResult<PerDeptVo> selectPerDeptByPrimaryKey(String id) throws Exception {
		SingleResult<PerDeptVo> rst = new SingleResult<PerDeptVo>();
		rst.setValue(this.perDeptDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePerDeptByPerDeptId(String id, UserProp userProp) throws Exception {
		this.perDeptDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除单位", "单位", String.valueOf(id), String.valueOf(id), "单位", userProp);
		return new MessageResponse(0, "单位删除完成！");
	}
}
