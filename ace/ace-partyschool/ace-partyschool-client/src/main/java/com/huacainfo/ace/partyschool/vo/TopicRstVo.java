package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.model.TopicRst;

import java.util.List;


public class TopicRstVo extends TopicRst {
private static final long serialVersionUID = 1L;

    private List<TopicOptRst> topicOptRstList;

    private String trName;

    private  String trScore;

    public String getTrName() {
        return trName;
    }

    public void setTrName(String trName) {
        this.trName = trName;
    }

    public String getTrScore() {
        return trScore;
    }

    public void setTrScore(String trScore) {
        this.trScore = trScore;
    }

    public List<TopicOptRst> getTopicOptRstList() {
        return topicOptRstList;
    }

    public void setTopicOptRstList(List<TopicOptRst> topicOptRstList) {
        this.topicOptRstList = topicOptRstList;
    }
}
