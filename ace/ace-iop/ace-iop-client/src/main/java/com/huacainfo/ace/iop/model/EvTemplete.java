package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTemplete implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String evTempleteId;

    private String evName;

    private String category;

    private String evDiscription;

    private String status;

    private Date createTime;

    private String evContent;

    public String getEvTempleteId() {
        return evTempleteId;
    }

    public void setEvTempleteId(String evTempleteId) {
        this.evTempleteId = evTempleteId == null ? null : evTempleteId.trim();
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName == null ? null : evName.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getEvDiscription() {
        return evDiscription;
    }

    public void setEvDiscription(String evDiscription) {
        this.evDiscription = evDiscription == null ? null : evDiscription.trim();
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

    public String getEvContent() {
        return evContent;
    }

    public void setEvContent(String evContent) {
        this.evContent = evContent == null ? null : evContent.trim();
    }
}