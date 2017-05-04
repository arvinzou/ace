package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.MeetingTopic;

public interface MeetingTopicDao {
    int deleteByPrimaryKey(String id);

    int insert(MeetingTopic record);

    int insertSelective(MeetingTopic record);

    MeetingTopic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingTopic record);

    int updateByPrimaryKey(MeetingTopic record);
}