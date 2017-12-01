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
import com.huacainfo.ace.uf.dao.PeiXunDao;
import com.huacainfo.ace.uf.model.PeiXun;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.uf.service.PeiXunService;
import com.huacainfo.ace.uf.vo.PeiXunVo;
import com.huacainfo.ace.uf.vo.PeiXunQVo;

/**
 * @author AFOC
 */
@Service("peiXunService")
public class PeiXunServiceImpl implements PeiXunService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PeiXunDao peiXunDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Override
	public PageResult<PeiXunVo> findPeiXunList(PeiXunQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<PeiXunVo> rst = new PageResult<PeiXunVo>();
		List<PeiXunVo> list = this.peiXunDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.peiXunDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	@Override
	public MessageResponse insertPeiXun(PeiXun o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());

		this.logger.info(o.getLongitude().toString());
		int temp = this.peiXunDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "推文名称重复！");
		}
		o.setCreateDate(new Date());
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.peiXunDao.insert(o);
		this.dataBaseLogService.log("添加推文", "推文", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加推文完成！");
	}

	@Override
	public MessageResponse updatePeiXun(PeiXun o, UserProp userProp) throws Exception {
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.peiXunDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更推文", "推文", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "变更推文完成！");
	}

	@Override
	public SingleResult<PeiXunVo> selectPeiXunByPrimaryKey(String id) throws Exception {
		SingleResult<PeiXunVo> rst = new SingleResult<PeiXunVo>();
		rst.setValue((PeiXunVo) this.peiXunDao.selectPeiXunByPrimaryKey(id));
		return rst;
	}

	@Override
	public MessageResponse deletePeiXunByPeiXunId(String id,
			UserProp userProp) throws Exception {
		this.peiXunDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除推文", "推文", String.valueOf(id),
				String.valueOf(id), "推文", userProp);
		return new MessageResponse(0, "推文删除完成！");
	}
}
