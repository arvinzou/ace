package com.huacainfo.ace.weui.service;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.weui.model.Feedback;

public interface FeedbackService {

	public abstract MessageResponse insertFeedback(Feedback o)
			throws Exception;
}
