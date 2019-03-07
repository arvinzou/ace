package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.model.TopicRst;

import java.util.List;


public class TopicRstVo extends TopicRst {
private static final long serialVersionUID = 1L;

private List<TopicOptRst> topicOptRstList;

    public List<TopicOptRst> getTopicOptRstList() {
        return topicOptRstList;
    }

    public void setTopicOptRstList(List<TopicOptRst> topicOptRstList) {
        this.topicOptRstList = topicOptRstList;
    }
}
