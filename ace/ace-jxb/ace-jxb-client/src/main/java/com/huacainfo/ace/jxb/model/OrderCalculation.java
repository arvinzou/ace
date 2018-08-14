package com.huacainfo.ace.jxb.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCalculation {
    private String id;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 咨询师编号（卖家）
     */
    private String counselorId;
    /**
     * 分配给卖家比例
     */
    private BigDecimal ratio;
    /**
     * 卖家获得金额
     */
    private BigDecimal amount;
    /**
     * 平台获得金额
     */
    private BigDecimal platformAmount;
    /**
     * 工作室抽成比例
     */
    private BigDecimal studioRatio;
    /**
     * 工作室获得金额
     */
    private BigDecimal studioAmount;
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

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    public BigDecimal getStudioRatio() {
        return studioRatio;
    }

    public void setStudioRatio(BigDecimal studioRatio) {
        this.studioRatio = studioRatio;
    }

    public BigDecimal getStudioAmount() {
        return studioAmount;
    }

    public void setStudioAmount(BigDecimal studioAmount) {
        this.studioAmount = studioAmount;
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