package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Task;


public class TaskVo extends Task {
private static final long serialVersionUID = 1L;
private  String tname;
private String tid;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
