package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TestTopic;
import com.huacainfo.ace.partyschool.model.Topic;
import com.huacainfo.ace.partyschool.model.TopicOpt;

import java.util.List;


public class TopicVo extends Topic {
private static final long serialVersionUID = 1L;
private  String tid;
private String tindex;
private String tscore;

private  String tname;

private List<TopicOpt> topicOptList;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public List<TopicOpt> getTopicOptList() {
        return topicOptList;
    }

    public void setTopicOptList(List<TopicOpt> topicOptList) {
        this.topicOptList = topicOptList;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTindex() {
        return tindex;
    }

    public void setTindex(String tindex) {
        this.tindex = tindex;
    }

    public String getTscore() {
        return tscore;
    }

    public void setTscore(String tscore) {
        this.tscore = tscore;
    }
}
