package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Scheduler;


public class SchedulerQVo extends Scheduler {


    private String keyword;

    private String linkId;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
