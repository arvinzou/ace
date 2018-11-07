package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.ActivityReport;

import java.util.List;


public class ActivityReportQVo extends ActivityReport {
    private static final long serialVersionUID = 1L;
    private String category;

    private String aStatus;

    private List<String> clist;


    public List<String> getClist() {
        return clist;
    }

    public void setClist(List<String> clist) {
        this.clist = clist;
    }

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
