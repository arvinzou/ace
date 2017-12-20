package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.NormFiles;
import com.huacainfo.ace.operana.vo.NormFilesVo;
import com.huacainfo.ace.operana.vo.NormFilesQVo;

import java.util.Map;

public interface NormFilesService {
	

	public abstract MessageResponse insertNormFiles(NormFiles obj,UserProp userProp) throws Exception;

	public abstract MessageResponse deleteNormFilesByNormFilesId(String id,UserProp userProp) throws Exception;

	public abstract Map<String, Object> selectFilesByMeetingTopicNormId(String meetingId,String topicId,String normId) throws Exception;

	
}
