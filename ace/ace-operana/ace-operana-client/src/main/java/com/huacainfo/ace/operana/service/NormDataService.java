package com.huacainfo.ace.operana.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;

import java.util.List;
import java.util.Map;

public interface NormDataService {

	public abstract MessageResponse deleteNormDataByNormDataId(String id, UserProp userProp) throws Exception;

	public MessageResponse importXls(List<Map<String, Object>> list, Map<String,Object> params,String sheetName,UserProp userProp)
			throws Exception;

	public List<Map<String,Object>> selectNormByMeetingAndTopicId(String meetingId,String topicId) throws Exception;

	public List<Map<String,Object>> selectTopicByMeetingId(String meetingId) throws Exception;

	public Map<String,String> selectTopicDictByMeetingId(String meetingId) throws Exception;

}
