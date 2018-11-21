package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopQuestionnaireResult;


public class FopQuestionnaireResultVo extends FopQuestionnaireResult {
    private static final long serialVersionUID = 1L;

    private String answerName;

    private String requestTopic;

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getRequestTopic() {
        return requestTopic;
    }

    public void setRequestTopic(String requestTopic) {
        this.requestTopic = requestTopic;
    }
}
