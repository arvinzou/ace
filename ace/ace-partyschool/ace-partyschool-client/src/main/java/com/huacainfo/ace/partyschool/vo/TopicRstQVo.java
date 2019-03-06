package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.model.TopicRst;

import java.util.List;


public class TopicRstQVo extends TopicRst {
    private static final long serialVersionUID = 1L;
    private List<TopicOptRst> topicOptList;

    private String tname;

    private  String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public List<TopicOptRst> getTopicOptList() {
        return topicOptList;
    }

    public void setTopicOptList(List<TopicOptRst> topicOptList) {
        this.topicOptList = topicOptList;
    }
}
