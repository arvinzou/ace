package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.model.MeetingTopic;
import com.huacainfo.ace.operana.model.MeetingUser;
import com.huacainfo.ace.operana.vo.MeetingVo;
import com.huacainfo.ace.operana.vo.MeetingQVo;

import java.util.List;
import java.util.Map;

public interface MeetingService {
	
	public abstract PageResult<MeetingVo> findMeetingList(MeetingQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertMeeting(Meeting obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateMeeting(Meeting obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Meeting> selectMeetingByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteMeetingByMeetingId(String id,UserProp userProp) throws Exception;



	public abstract List<Map<String,Object>> selectAllTopic(String meetingId,String name);

	public abstract Map<String, Object> selectTopicByMeetingId(String meetingId) throws Exception;

	public abstract MessageResponse insertMeetingTopic(List<MeetingTopic> list, String meetingId,boolean del, UserProp userProp) throws Exception;


	public abstract MessageResponse updateTopicOwner(String userId,String meetingId,String topicId, UserProp userProp)throws Exception;

	public abstract MessageResponse deleteByMeetingIdAndTopicId(String meetingId,String topicId, UserProp userProp)throws Exception;


	/*
	* */
	public abstract List<Map<String,Object>> selectAllUserDeptId(String meetingId,String name);

	public abstract Map<String, Object> selectUserByMeetingId(String meetingId) throws Exception;

	public abstract MessageResponse insertMeetingUser(List<MeetingUser> list, String meetingId,boolean del, UserProp userProp) throws Exception;


	public abstract MessageResponse updateMandatory(String userId,String meetingId,String mandatory, UserProp userProp)throws Exception;

	public abstract MessageResponse deleteByMeetingIdAndUserId(String meetingId,String userId, UserProp userProp)throws Exception;
	
}
