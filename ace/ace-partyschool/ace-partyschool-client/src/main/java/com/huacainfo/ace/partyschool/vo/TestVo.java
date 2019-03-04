package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Test;

import java.util.List;


public class TestVo extends Test {
    private static final long serialVersionUID = 1L;
    private List<TopicVo> topics;

    public List<TopicVo> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicVo> topics) {
        this.topics = topics;
    }
}
