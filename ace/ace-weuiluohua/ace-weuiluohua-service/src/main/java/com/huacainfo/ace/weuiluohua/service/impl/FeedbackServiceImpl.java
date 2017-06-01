package com.huacainfo.ace.weuiluohua.service.impl;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.weuiluohua.dao.FeedbackDao;
import com.huacainfo.ace.weuiluohua.model.Feedback;
import com.huacainfo.ace.weuiluohua.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{
	private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private FeedbackDao feedbackDao;
	public  MessageResponse insertFeedback(Feedback o)
			throws Exception{
		if(CommonUtils.isBlank(o.getTitle())){
			return new MessageResponse(1,"提交的标题不能为空！");
		}
		if(CommonUtils.isBlank(o.getDocText())){
			return new MessageResponse(1,"提交的内容不能为空！");
		}
		o.setId(UUID.randomUUID().toString());
		o.setCreateDate(new Date());
		o.setStatus("1");
		this.feedbackDao.insert(o);
		return new MessageResponse(0,"成功！");
	}
}
