package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.ActivityReport;


public class ActivityReportVo extends ActivityReport {
    private static final long serialVersionUID = 1L;
    private String activityTitel;

    public String getActivityTitel() {
        return activityTitel;
    }

    public void setActivityTitel(String activityTitel) {
        this.activityTitel = activityTitel;
    }
}
