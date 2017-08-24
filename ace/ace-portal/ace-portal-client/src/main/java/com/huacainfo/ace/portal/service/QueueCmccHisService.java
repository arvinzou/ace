package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.vo.QueueCmccHisQVo;
import com.huacainfo.ace.portal.vo.QueueCmccHisVo;
public interface QueueCmccHisService {
	public abstract PageResult<QueueCmccHisVo> findQueueCmccHisList(QueueCmccHisQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse deleteQueueCmccHisByQueueCmccHisId(String id, UserProp userProp) throws Exception;

}