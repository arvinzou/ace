package com.huacainfo.ace.portal.service.impl;

import java.util.List;

import com.huacainfo.ace.common.result.PageResult;
import org.apache.log4j.Logger;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.portal.dao.QueueCmccWaitMapper;
import com.huacainfo.ace.portal.service.QueueCmccWaitService;
import com.huacainfo.ace.portal.vo.QueueCmccWaitQVo;
import com.huacainfo.ace.portal.vo.QueueCmccWaitVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queueCmccWaitService")
public class QueueCmccWaitServiceImpl implements QueueCmccWaitService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private QueueCmccWaitMapper queueCmccWaitMapper;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	public PageResult<QueueCmccWaitVo> findQueueCmccWaitList(QueueCmccWaitQVo condition, int start,
															 int limit, String orderBy) throws Exception {
		PageResult<QueueCmccWaitVo> rst = new PageResult<QueueCmccWaitVo>();
		List<QueueCmccWaitVo> list = this.queueCmccWaitMapper.findList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.queueCmccWaitMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	
	public MessageResponse deleteQueueCmccWaitByQueueCmccWaitId(String id,
			UserProp userProp) throws Exception {
		MessageResponse rst = new MessageResponse();
		this.queueCmccWaitMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除待发短信", "待发短信", String.valueOf(id), String.valueOf(id),
				"待发短信", userProp);
		return rst;
	}

}
