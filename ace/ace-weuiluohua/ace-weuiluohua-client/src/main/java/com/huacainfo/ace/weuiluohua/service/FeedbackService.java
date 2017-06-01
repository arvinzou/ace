package com.huacainfo.ace.weuiluohua.service;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.weuiluohua.model.Feedback;

public interface FeedbackService {

	public abstract MessageResponse insertFeedback(Feedback o)
			throws Exception;
}
