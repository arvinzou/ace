package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsultProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String counselorId;
    /**
     * 咨询类型(1语音2视频3面对面)
     */
    private String type;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 是否接收咨询 0-否 1-是
     */
    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId == null ? null : counselorId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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