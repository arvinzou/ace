package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderCalculation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String orderId;

    private String counselorId;

    private BigDecimal ratio;

    private BigDecimal amount;
    /**
     * 计算标记 0-未计算 1-已计算
     */
    private String cpuTag;
    /**
     * 发放标记 0-未发放 1-已发放
     */
    private String grantTag;

    private String remark;

    private String status;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId == null ? null : counselorId.trim();
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCpuTag() {
        return cpuTag;
    }

    public void setCpuTag(String cpuTag) {
        this.cpuTag = cpuTag == null ? null : cpuTag.trim();
    }

    public String getGrantTag() {
        return grantTag;
    }

    public void setGrantTag(String grantTag) {
        this.grantTag = grantTag == null ? null : grantTag.trim();
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}