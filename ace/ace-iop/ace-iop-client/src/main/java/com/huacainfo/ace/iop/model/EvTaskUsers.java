package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTaskUsers implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String id;

    private String evTaskId;

    private String userId;

    private String grade;

    private String ip;

    private Date taskTime;

    private Date evTime;

    private String status;

    private Date createTime;

    private String advise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvTaskId() {
        return evTaskId;
    }

    public void setEvTaskId(String evTaskId) {
        this.evTaskId = evTaskId == null ? null : evTaskId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public Date getEvTime() {
        return evTime;
    }

    public void setEvTime(Date evTime) {
        this.evTime = evTime;
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

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise == null ? null : advise.trim();
    }
}