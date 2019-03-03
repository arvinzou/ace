package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TestTopic;

import java.util.List;

/**
 * @program: ace
 * @description:
 * @author: 🥦003
 * @create: 2019-03-02 10:41
 **/
public class TestTopicQVo extends TestTopic {
    private static final long serialVersionUID = 1L;
    private List<String> topics;

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
