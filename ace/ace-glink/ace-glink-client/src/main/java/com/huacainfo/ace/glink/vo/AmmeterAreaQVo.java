package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.AmmeterArea;


public class AmmeterAreaQVo extends AmmeterArea {

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
}
