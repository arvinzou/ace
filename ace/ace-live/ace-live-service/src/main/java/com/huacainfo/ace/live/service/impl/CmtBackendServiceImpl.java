package com.huacainfo.ace.live.service.impl;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.live.model.LiveCmt;
import com.huacainfo.ace.live.service.LiveCmtService;
import com.huacainfo.ace.live.service.WWWService;
import com.huacainfo.ace.portal.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("cmt")
public class CmtBackendServiceImpl implements BackendService {
	private static final Logger logger = LoggerFactory.getLogger(CmtBackendServiceImpl.class);
	@Autowired
	private LiveCmtService liveCmtService;
	@Override
	public  MessageResponse service(Map<String, Object> data) throws Exception {
		String jsons=(String) data.get("jsons");
		LiveCmt obj = JSON.parseObject(jsons, LiveCmt.class);
		MessageResponse rst=this.liveCmtService.insertLiveCmt(obj);
		return rst;
	}
}
