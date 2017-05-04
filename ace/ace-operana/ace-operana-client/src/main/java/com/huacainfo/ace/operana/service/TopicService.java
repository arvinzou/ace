package com.huacainfo.ace.operana.service;

import java.util.List;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.Topic;
import com.huacainfo.ace.operana.model.TopicNorm;
import com.huacainfo.ace.operana.vo.TopicQVo;
import com.huacainfo.ace.operana.vo.TopicVo;

public interface TopicService {
	
	public abstract PageResult<TopicVo> findTopicList(TopicQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertTopic(Topic obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateTopic(Topic obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Topic> selectTopicByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteTopicByTopicId(String id,UserProp userProp) throws Exception;
	public abstract MessageResponse insertTopicNorm(List<TopicNorm> obj,String topicId,boolean del,UserProp userProp) throws Exception;


}
