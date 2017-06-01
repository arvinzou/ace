package com.huacainfo.ace.luohua.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.luohua.model.Feedback;
import com.huacainfo.ace.luohua.vo.FeedbackVo;
import com.huacainfo.ace.luohua.vo.FeedbackQVo;
public interface FeedbackService {
	
	public abstract PageResult<FeedbackVo> findFeedbackList(FeedbackQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertFeedback(Feedback obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateFeedback(Feedback obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Feedback> selectFeedbackByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteFeedbackByFeedbackId(String id,UserProp userProp) throws Exception;

	
}
