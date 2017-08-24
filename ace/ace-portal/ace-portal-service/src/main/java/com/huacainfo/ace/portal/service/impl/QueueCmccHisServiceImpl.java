package com.huacainfo.ace.portal.service.impl;

import java.util.List;

import com.huacainfo.ace.common.result.PageResult;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.portal.dao.QueueCmccHisMapper;
import com.huacainfo.ace.portal.service.QueueCmccHisService;
import com.huacainfo.ace.portal.vo.QueueCmccHisQVo;
import com.huacainfo.ace.portal.vo.QueueCmccHisVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queueCmccHisService")
public class QueueCmccHisServiceImpl implements QueueCmccHisService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccHisMapper queueCmccHisMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public PageResult<QueueCmccHisVo> findQueueCmccHisList(QueueCmccHisQVo condition, int start,
														   int limit, String orderBy) throws Exception {
		PageResult<QueueCmccHisVo> rst = new PageResult<QueueCmccHisVo>();
		List<QueueCmccHisVo> list = this.queueCmccHisMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.queueCmccHisMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse deleteQueueCmccHisByQueueCmccHisId(String id,
			UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.queueCmccHisMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除历史短信", "历史短信", String.valueOf(id), String.valueOf(id),
				"历史短信", userProp);
		return rst;
	}

}
