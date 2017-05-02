package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.MeetingUser;
import org.apache.ibatis.annotations.Param;

public interface MeetingUserDao {
    int deleteByMeetingId(String meetingId);

    int insert(MeetingUser record);

    int deleteByMeetingIdAndUserId(@Param("userId")String userId, @Param("meetingId")String meetingId);
}