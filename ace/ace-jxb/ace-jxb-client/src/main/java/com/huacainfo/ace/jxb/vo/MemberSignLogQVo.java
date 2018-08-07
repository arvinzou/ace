package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.MemberSignLog;


public class MemberSignLogQVo extends MemberSignLog {
    private static final long serialVersionUID = 1L;

    /**
     * 月时间 demo： 2018-08
     */
    private String monthDate;

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }
}
