package com.huacainfo.ace.operana.service;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.operana.vo.NormDetailQVo;
import com.huacainfo.ace.operana.vo.NormDetailVo;

public interface NormDetailService {

	public abstract MessageResponse deleteNormDetailByNormDetailId(String id, UserProp userProp) throws Exception;

	public MessageResponse importXls(List<Map<String, Object>> list, Map<String,Object> params, String sheetName, UserProp userProp)
			throws Exception;

	public List<Map<String,Object>> selectNormDetailByMeetingAndTopicIdAndNormId(String meetingId,String topicId,String normId) throws Exception;



	public Map<String,String> selectNormDictByTopicId(String topicId) throws Exception;


	public abstract List<Map<String,Object>> selectNormByTopicId(String topicId) throws Exception;


	public abstract PageResult<NormDetailVo> findNormDetailList(NormDetailQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse saveOrUpdateNormDetail(NormDetailVo obj, UserProp userProp) throws Exception;
	
}
