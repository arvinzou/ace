package com.huacainfo.ace.operana.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.vo.MeetingQVo;
import com.huacainfo.ace.operana.vo.MeetingVo;

public interface MeetingDao {
	int deleteByPrimaryKey(String MeetingId);

	int insert(Meeting record);

	MeetingVo selectByPrimaryKey(String MeetingId);

	int updateByPrimaryKey(Meeting record);

	List<MeetingVo> findList(@Param("condition") MeetingQVo condition, @Param("start") int start,
			@Param("limit") int limit, @Param("orderBy") String orderBy);

	int findCount(@Param("condition") MeetingQVo condition);

	int isExit(Meeting record);

	List<Map<String, Object>> selectAllTopicCategory();

	List<Map<String, Object>> selectTopicByCategory(@Param("category") String category,
			@Param("meetingId") String meetingId);

	List<Map<String, Object>> selectTopicByMeetingId(String meetingId);

    List<Map<String, Object>> selectTopicByName(@Param("name")String name,@Param("meetingId") String meetingId);



	List<Map<String, Object>> selectAllUserDeptId();

	List<Map<String, Object>> selectUserByDeptId(@Param("deptId") String deptId,
													@Param("meetingId") String meetingId);

	List<Map<String, Object>> selectUserByMeetingId(String meetingId);

	List<Map<String, Object>> selectUserByName(@Param("name")String name,@Param("meetingId") String meetingId);


	List<Map<String, Object>> selectTaskForEmail(@Param("tasktime")Integer tasktime);

	List<Map<String, Object>> selectEmailForNotice();

	List<Map<String, Object>> selectLiableEmailForNotice();

}