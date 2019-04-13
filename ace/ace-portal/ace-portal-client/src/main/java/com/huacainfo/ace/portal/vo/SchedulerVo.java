package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Scheduler;


public class SchedulerVo extends Scheduler {

    private String linkId;

    private String checked;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
