package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuDonateList;


public class CuDonateListQVo extends CuDonateList {
    private static final long serialVersionUID = 1L;

    /**
     * 今日时间
     */
    private String todayDate;

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }
}
