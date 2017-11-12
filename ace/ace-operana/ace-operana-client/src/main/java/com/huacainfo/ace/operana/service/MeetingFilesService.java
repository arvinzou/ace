package com.huacainfo.ace.operana.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.operana.model.MeetingFiles;
import com.huacainfo.ace.operana.vo.MeetingFilesVo;
import com.huacainfo.ace.operana.vo.MeetingFilesQVo;

import java.util.Map;

public interface MeetingFilesService {

	public abstract MessageResponse insertMeetingFiles(MeetingFiles obj,UserProp userProp) throws Exception;
	public abstract MessageResponse deleteMeetingFilesByMeetingFilesId(String id,UserProp userProp) throws Exception;
	public abstract Map<String, Object> selectFilesByMeetingId(String meetingId) throws Exception;

	
}
