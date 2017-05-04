package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.Topic;

public interface TopicDao {
    int deleteByPrimaryKey(String id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}