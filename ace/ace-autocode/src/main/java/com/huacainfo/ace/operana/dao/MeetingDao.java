package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.Meeting;

public interface MeetingDao {
    int deleteByPrimaryKey(String id);

    int insert(Meeting record);

    int insertSelective(Meeting record);

    Meeting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKey(Meeting record);
}