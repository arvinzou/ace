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
import com.huacainfo.ace.uf.dao.PerJobDao;
import com.huacainfo.ace.uf.model.PerJob;
import com.huacainfo.ace.uf.service.PerJobService;
import com.huacainfo.ace.uf.vo.PerJobQVo;
import com.huacainfo.ace.uf.vo.PerJobVo;
@Service("perJobService")
public class PerJobServiceImpl implements PerJobService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerJobDao perJobDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PerJobVo> findPerJobList(PerJobQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PerJobVo> rst = new PageResult<PerJobVo>();
		List<PerJobVo> list = this.perJobDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.perJobDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPerJob(PerJob o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
		int temp = this.perJobDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "职务名称重复！");
		}
		o.setCreateDate(new Date());

		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.perJobDao.insert(o);
		this.dataBaseLogService.log("添加职务", "职务", "", o.getJobId(),
				o.getJobId(), userProp);
		return new MessageResponse(0, "添加职务完成！");
	}

	public MessageResponse updatePerJob(PerJob o, UserProp userProp) throws Exception {

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.perJobDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更职务", "职务", "", o.getJobId(), o.getJobId(), userProp);
		return new MessageResponse(0, "变更职务完成！");
	}

	public SingleResult<PerJobVo> selectPerJobByPrimaryKey(String id) throws Exception {
		SingleResult<PerJobVo> rst = new SingleResult<PerJobVo>();
		rst.setValue(this.perJobDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePerJobByPerJobId(String id, UserProp userProp) throws Exception {
		this.perJobDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除职务", "职务", String.valueOf(id), String.valueOf(id), "职务", userProp);
		return new MessageResponse(0, "职务删除完成！");
	}
}
