package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Topic;
import com.huacainfo.ace.partyschool.model.TopicOpt;

import java.util.List;


public class TopicQVo extends Topic {
    private static final long serialVersionUID = 1L;

    private  List<TopicOpt> topicOptList;

    private String testId;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public List<TopicOpt> getTopicOptList() {
        return topicOptList;
    }

    public void setTopicOptList(List<TopicOpt> topicOptList) {
        this.topicOptList = topicOptList;
    }
}
