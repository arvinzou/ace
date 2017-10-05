package com.huacainfo.ace.operana.dao;

import com.huacainfo.ace.operana.model.MeetingTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingTopicDao {
    int deleteByMeetingId(String meetingId);

    int insert(MeetingTopic record);

    int updateTopicOwner(@Param("userId")String userId,@Param("meetingId")String meetingId,@Param("topicId")String topicId);

    int deleteByMeetingIdAndTopicId(@Param("meetingId")String meetingId,@Param("topicId")String topicId);

    List<MeetingTopic>  selectTopicByMeetingId(@Param("meetingId")String meetingId);
}