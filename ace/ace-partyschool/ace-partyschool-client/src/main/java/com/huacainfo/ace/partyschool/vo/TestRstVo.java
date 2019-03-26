package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TestRst;

import java.util.List;


public class TestRstVo extends TestRst {
private static final long serialVersionUID = 1L;

    private List<TopicRstVo> topicRstVoList;

    public List<TopicRstVo> getTopicRstVoList() {
        return topicRstVoList;
    }

    public void setTopicRstVoList(List<TopicRstVo> topicRstVoList) {
        this.topicRstVoList = topicRstVoList;
    }
}
