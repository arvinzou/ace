package com.huacainfo.ace.partyschool.model;

import java.util.Date;

public class TopicOptRst {
    private String id;

    private String topicId;

    private String content;

    private String answer;

    private String youAnswer;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getYouAnswer() {
        return youAnswer;
    }

    public void setYouAnswer(String youAnswer) {
        this.youAnswer = youAnswer == null ? null : youAnswer.trim();
    }

}