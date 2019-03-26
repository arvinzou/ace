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

    private String startDate;
    private String endDate;

    private String clsId;

    public String getClsId() {
        return clsId;
    }

    public void setClsId(String clsId) {
        this.clsId = clsId;
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
