package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.ActivityReport;


public class ActivityReportVo extends ActivityReport {
    private static final long serialVersionUID = 1L;
    private String activityTitel;

    private String category;

    private String headimgurl;

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivityTitel() {
        return activityTitel;
    }

    public void setActivityTitel(String activityTitel) {
        this.activityTitel = activityTitel;
    }
}
