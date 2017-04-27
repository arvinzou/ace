package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.TopicNorm;

public interface TopicNormDao {

	int insert(TopicNorm record);

	int deleteByTopicId(String topicId);

}