package com.huacainfo.ace.operana.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.operana.model.NormData;
import com.huacainfo.ace.operana.vo.NormDataQVo;
import com.huacainfo.ace.operana.vo.NormDataVo;

import java.util.List;
import java.util.Map;

public interface NormDataService {

	public abstract MessageResponse deleteNormDataByNormDataId(String id, UserProp userProp) throws Exception;

	public MessageResponse importXls(List<Map<String, Object>> list, Map<String,Object> params,String sheetName,UserProp userProp)
			throws Exception;

	public List<Map<String,Object>> selectNormByMeetingAndTopicId(String meetingId,String topicId) throws Exception;

	public List<Map<String,Object>> selectTopicByMeetingId(String meetingId) throws Exception;

	public Map<String,String> selectTopicDictByMeetingId(String meetingId) throws Exception;


	public abstract PageResult<NormDataVo> findNormDataList(NormDataQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse saveOrUpdateNormData(NormDataVo obj, UserProp userProp) throws Exception;


}
