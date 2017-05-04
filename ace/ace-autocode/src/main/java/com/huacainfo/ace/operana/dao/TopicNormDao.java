package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.TopicNorm;

public interface TopicNormDao {
    int deleteByPrimaryKey(String id);

    int insert(TopicNorm record);

    int insertSelective(TopicNorm record);

    TopicNorm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TopicNorm record);

    int updateByPrimaryKey(TopicNorm record);
}