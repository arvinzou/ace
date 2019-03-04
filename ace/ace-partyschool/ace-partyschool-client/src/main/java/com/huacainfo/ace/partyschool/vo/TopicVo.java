package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.TestTopic;
import com.huacainfo.ace.partyschool.model.Topic;


public class TopicVo extends Topic {
private static final long serialVersionUID = 1L;
private  String tid;
private String tindex;
private String tscore;

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
