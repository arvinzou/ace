package com.huacainfo.ace.live.service.impl;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.live.service.WWWService;
import com.huacainfo.ace.portal.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("like")
public class LikeBackendServiceImpl implements BackendService {
	private static final Logger logger = LoggerFactory.getLogger(LikeBackendServiceImpl.class);
	@Autowired
	private WWWService wWWService;
	@Override
	public  MessageResponse service(Map<String, Object> data) throws Exception {

		wWWService.updateRptLikeNum((String) data.get("id"),(String)data.get("type"));
		return new MessageResponse(0,"OK");
	}
}
