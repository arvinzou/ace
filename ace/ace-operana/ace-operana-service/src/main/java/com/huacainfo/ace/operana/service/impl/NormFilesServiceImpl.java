package com.huacainfo.ace.operana.service.impl;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.NormFilesDao;
import com.huacainfo.ace.operana.model.NormFiles;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.operana.service.NormFilesService;
import com.huacainfo.ace.operana.vo.NormFilesVo;
import com.huacainfo.ace.operana.vo.NormFilesQVo;
@Service("normFilesService")
public class NormFilesServiceImpl implements NormFilesService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormFilesDao normFilesDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    @Override
	public MessageResponse insertNormFiles(NormFiles o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.normFilesDao.insert(o);
		this.dataBaseLogService.log("添加指标文件", "指标文件", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加指标文件完成！");
	}

    @Override
	public MessageResponse deleteNormFilesByNormFilesId(String id,
			UserProp userProp) throws Exception {
		this.normFilesDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除指标文件", "指标文件", String.valueOf(id),
				String.valueOf(id), "指标文件", userProp);
		return new MessageResponse(0, "指标文件删除完成！");
	}
	@Override
	public  Map<String, Object> selectFilesByMeetingTopicNormId(String meetingId, String topicId, String normId) throws Exception{
		Map<String, Object> rst=new HashMap<String, Object>();
		List<Map<String, Object>> data=this.normFilesDao.selectFilesByMeetingTopicNormId(meetingId,topicId,normId);
		rst.put("data",data);
		return rst;
	}
}
