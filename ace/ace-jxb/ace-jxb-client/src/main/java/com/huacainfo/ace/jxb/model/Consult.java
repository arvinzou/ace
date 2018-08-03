package com.huacainfo.ace.jxb.model;

import java.util.Date;

public class Consult {
    /**
     * 咨询师ID  counselor.id
     */
    private String id;

    /**
     * 咨询对象
     */
    private String objects;
    /**
     * 擅长领域
     */
    private String field;
    /**
     * 每次咨询时长（分钟）
     */
    private String procTime;
    /**
     * 预约须知
     */
    private String info;
    /**
     * 面对面咨询仅限城市
     */
    private String city;

    /**
     * 在线状态 0-离线 1-在线
     */
    private String onlineStatus;

    /**
     * 是否接收咨询 0-否 1-是
     */
    private String status;
    /**
     * 入库日期
     */
    private Date createDate;

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects == null ? null : objects.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getProcTime() {
        return procTime;
    }

    public void setProcTime(String procTime) {
        this.procTime = procTime == null ? null : procTime.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}