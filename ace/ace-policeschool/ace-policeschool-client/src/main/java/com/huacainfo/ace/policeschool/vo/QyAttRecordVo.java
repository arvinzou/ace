package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.QyAttRecord;


public class QyAttRecordVo extends QyAttRecord {
    private static final long serialVersionUID = 1L;
    /**
     * 标准化时间格式
     */
    private String attenDateTime;

    public String getAttenDateTime() {
        return attenDateTime;
    }

    public void setAttenDateTime(String attenDateTime) {
        this.attenDateTime = attenDateTime;
    }
}
