package com.huacainfo.ace.jxb.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawRecord extends BaseModel {
    private String id;
    /**
     * 咨询师编号
     */
    private String counselorId;
    /**
     * 咨询师openId
     */
    private String openId;
    /**
     * 提现方式 1-微信提现 2-线下打款
     */
    private String withdrawType;
    /**
     * 申请金额
     */
    private BigDecimal applyAmount;
    /**
     * 税费/其它扣减金额
     */
    private BigDecimal taxAmount;
    /**
     * 实际提现金额
     */
    private BigDecimal actAmount;
    /**
     * 审核状态 temp-审核中 pass-审核通过 reject-审核失败
     */
    private String auditRst;

    private String auditRemark;

    private String remark;

    private String status;

    private Date createDate;

    private Date updateDate;

    /**
     * 真实姓名
     */
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType == null ? null : withdrawType.trim();
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getActAmount() {
        return actAmount;
    }

    public void setActAmount(BigDecimal actAmount) {
        this.actAmount = actAmount;
    }

    public String getAuditRst() {
        return auditRst;
    }

    public void setAuditRst(String auditRst) {
        this.auditRst = auditRst == null ? null : auditRst.trim();
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
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