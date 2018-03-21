package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTaskDataSub implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private String evTaskDataId;
    private String ip;
    private String userId;
    private String feedback;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvTaskDataId() {
        return evTaskDataId;
    }

    public void setEvTaskDataId(String evTaskDataId) {
        this.evTaskDataId = evTaskDataId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}