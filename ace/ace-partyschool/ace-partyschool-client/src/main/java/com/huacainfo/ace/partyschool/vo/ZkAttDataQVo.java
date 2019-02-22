package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ZkAttData;


public class ZkAttDataQVo extends ZkAttData {
    private static final long serialVersionUID = 1L;

    /**
     * 查询年月日
     */
    private String dateTimeStr;

    public String getDateTimeStr() {
        return dateTimeStr;
    }

    public void setDateTimeStr(String dateTimeStr) {
        this.dateTimeStr = dateTimeStr;
    }
}
