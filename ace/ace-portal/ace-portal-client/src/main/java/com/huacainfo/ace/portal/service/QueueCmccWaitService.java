package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.vo.QueueCmccWaitQVo;
import com.huacainfo.ace.portal.vo.QueueCmccWaitVo;
public interface QueueCmccWaitService {
	public abstract PageResult<QueueCmccWaitVo> findQueueCmccWaitList(QueueCmccWaitQVo condition, int start, int limit, String orderBy) throws Exception;
		public abstract MessageResponse deleteQueueCmccWaitByQueueCmccWaitId(String id, UserProp userProp) throws Exception;

}
