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
import com.huacainfo.ace.uf.dao.PerResumeDao;
import com.huacainfo.ace.uf.model.PerResume;
import com.huacainfo.ace.uf.service.PerResumeService;
import com.huacainfo.ace.uf.vo.PerResumeQVo;
import com.huacainfo.ace.uf.vo.PerResumeVo;
@Service("perResumeService")
public class PerResumeServiceImpl implements PerResumeService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PerResumeDao perResumeDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<PerResumeVo> findPerResumeList(PerResumeQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<PerResumeVo> rst = new PageResult<PerResumeVo>();
		List<PerResumeVo> list = this.perResumeDao.findList(condition, start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.perResumeDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertPerResume(PerResume o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		// o.setId(String.valueOf(new Date().getTime()));

		int temp = this.perResumeDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "简历名称重复！");
		}
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.perResumeDao.insert(o);
		this.dataBaseLogService.log("添加简历", "简历", "", o.getJobId(), o.getJobId(), userProp);
		return new MessageResponse(0, "添加简历完成！");
	}

	public MessageResponse updatePerResume(PerResume o, UserProp userProp) throws Exception {

		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.perResumeDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更简历", "简历", "", o.getJobId(), o.getJobId(), userProp);
		return new MessageResponse(0, "变更简历完成！");
	}

	public SingleResult<PerResumeVo> selectPerResumeByPrimaryKey(String id) throws Exception {
		SingleResult<PerResumeVo> rst = new SingleResult<PerResumeVo>();
		rst.setValue(this.perResumeDao.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deletePerResumeByPerResumeId(String id, UserProp userProp) throws Exception {
		this.perResumeDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除简历", "简历", String.valueOf(id), String.valueOf(id), "简历", userProp);
		return new MessageResponse(0, "简历删除完成！");
	}
}
