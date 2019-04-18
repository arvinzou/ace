package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeAlarmData extends BaseModel {
    private String id;

    private String areaNodeID;

    private String alarmType;

    private String alarmContent;

    private String alarmDateTime;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaNodeID() {
        return areaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        this.areaNodeID = areaNodeID == null ? null : areaNodeID.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent == null ? null : alarmContent.trim();
    }

    public String getAlarmDateTime() {
        return alarmDateTime;
    }

    public void setAlarmDateTime(String alarmDateTime) {
        this.alarmDateTime = alarmDateTime == null ? null : alarmDateTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}