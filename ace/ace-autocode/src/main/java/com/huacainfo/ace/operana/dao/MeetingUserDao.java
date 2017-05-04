package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.MeetingUser;

public interface MeetingUserDao {
    int deleteByPrimaryKey(String id);

    int insert(MeetingUser record);

    int insertSelective(MeetingUser record);

    MeetingUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingUser record);

    int updateByPrimaryKey(MeetingUser record);
}