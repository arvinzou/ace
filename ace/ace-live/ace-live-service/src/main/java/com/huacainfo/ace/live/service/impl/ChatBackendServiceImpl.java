package com.huacainfo.ace.live.service.impl;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.live.model.LiveMsg;
import com.huacainfo.ace.live.service.LiveMsgService;
import com.huacainfo.ace.portal.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service("chat")
public class ChatBackendServiceImpl implements BackendService {
	private static final Logger logger = LoggerFactory.getLogger(ChatBackendServiceImpl.class);
	@Autowired
	private LiveMsgService liveMsgService;
	@Override
	public  MessageResponse service(Map<String, Object> data) throws Exception {
		logger.info("{}",data);
		LiveMsg o = new LiveMsg();
		o.setContent((String) data.get("message"));
		o.setRid((String)data.get("rid"));
		o.setUid((String)data.get("uid"));
		return this.liveMsgService.insertLiveMsg(o);
	}
}
