package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Topic;
import com.huacainfo.ace.partyschool.model.TopicOpt;

import java.util.List;


public class TopicQVo extends Topic {
    private static final long serialVersionUID = 1L;



    public List<TopicOpt> getOptions() {
        return options;
    }

    public void setOptions(List<TopicOpt> options) {
        this.options = options;
    }

    private  List<TopicOpt> options;

    private String testId;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}
