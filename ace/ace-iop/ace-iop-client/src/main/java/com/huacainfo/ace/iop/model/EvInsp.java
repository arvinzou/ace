package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvInsp implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String evInspId;

    private String evTaskId;

    private String evUserId;

    private String status;

    private Date createTime;

    public String getEvInspId() {
        return evInspId;
    }

    public void setEvInspId(String evInspId) {
        this.evInspId = evInspId == null ? null : evInspId.trim();
    }

    public String getEvTaskId() {
        return evTaskId;
    }

    public void setEvTaskId(String evTaskId) {
        this.evTaskId = evTaskId == null ? null : evTaskId.trim();
    }

    public String getEvUserId() {
        return evUserId;
    }

    public void setEvUserId(String evUserId) {
        this.evUserId = evUserId == null ? null : evUserId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}