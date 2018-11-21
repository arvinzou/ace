package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuDonateList;


public class CuDonateListQVo extends CuDonateList {
    private static final long serialVersionUID = 1L;

    /**
     * 今日时间
     */
    private String todayDate;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 微信用户昵称
     */
    private String nickname;

    /**
     * 是否需要收据
     * 0-不需要 1-需要
     */
    private String needReceipt;

    private String startDate;
    private String endDate;

    /**
     * 捐款单位名称
     */
    private String donatePostName;

    public String getDonatePostName() {
        return donatePostName;
    }

    public void setDonatePostName(String donatePostName) {
        this.donatePostName = donatePostName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(String needReceipt) {
        this.needReceipt = needReceipt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
