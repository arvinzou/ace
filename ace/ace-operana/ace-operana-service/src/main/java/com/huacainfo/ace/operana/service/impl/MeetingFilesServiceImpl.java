package com.huacainfo.ace.operana.service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.MeetingFilesDao;
import com.huacainfo.ace.operana.model.MeetingFiles;
import com.huacainfo.ace.operana.service.MeetingFilesService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
@Service("meetingFilesService")
public class MeetingFilesServiceImpl implements MeetingFilesService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MeetingFilesDao meetingFilesDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;



	public MessageResponse insertMeetingFiles(MeetingFiles o, UserProp userProp) throws Exception {
		o.setId(UUID.randomUUID().toString());
		if (CommonUtils.isBlank(o.getId())) {
			return new MessageResponse(1, "主键不能为空！");
		}
		if (CommonUtils.isBlank(o.getMeetingId())) {
			return new MessageResponse(1, "会议编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {
			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getUrl())) {
			return new MessageResponse(1, "地址不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}

		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.meetingFilesDao.insert(o);
		this.dataBaseLogService.log("添加会议文件", "会议文件", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加会议文件完成！");
	}



	public MessageResponse deleteMeetingFilesByMeetingFilesId(String id, UserProp userProp) throws Exception {
		this.meetingFilesDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除会议文件", "会议文件", String.valueOf(id), String.valueOf(id), "会议文件", userProp);
		return new MessageResponse(0, "会议文件删除完成！");
	}

	public Map<String, Object> selectFilesByMeetingId(String meetingId) throws Exception{
		Map<String, Object> rst=new HashMap<String, Object>();
		List<Map<String, Object>> data=this.meetingFilesDao.selectFilesByMeetingId(meetingId);
		rst.put("data",data);
		return rst;
	}
}
