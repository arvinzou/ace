package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.model.TopicRst;

import java.util.List;


public class TopicRstQVo extends TopicRst {
    private static final long serialVersionUID = 1L;
    private List<TopicOptRst> topicOptList;

    public List<TopicOptRst> getTopicOptList() {
        return topicOptList;
    }

    public void setTopicOptList(List<TopicOptRst> topicOptList) {
        this.topicOptList = topicOptList;
    }
}
