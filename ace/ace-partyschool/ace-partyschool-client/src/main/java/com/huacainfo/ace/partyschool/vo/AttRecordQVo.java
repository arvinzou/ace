package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.AttRecord;


public class AttRecordQVo extends AttRecord {
    private static final long serialVersionUID = 1L;


    /**
     * 查询年月日
     */
    private String dateTimeStr;
    /**
     * 姓名
     */
    private String name;


    public String getName() {
        return name == null ? name : name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTimeStr() {
        return dateTimeStr;
    }

    public void setDateTimeStr(String dateTimeStr) {
        this.dateTimeStr = dateTimeStr;
    }
}
