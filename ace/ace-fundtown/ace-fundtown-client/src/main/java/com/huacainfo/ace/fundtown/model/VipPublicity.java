package com.huacainfo.ace.fundtown.model;

import java.util.Date;

public class VipPublicity {
    private String id;

    private String deptId;

    private String belongDeptId;

    private String belongDeptName;

    private String delegate;

    private String creditNo;

    private Date zjxRecordDate;

    private String zjxRecordNo;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(String belongDeptId) {
        this.belongDeptId = belongDeptId == null ? null : belongDeptId.trim();
    }

    public String getBelongDeptName() {
        return belongDeptName;
    }

    public void setBelongDeptName(String belongDeptName) {
        this.belongDeptName = belongDeptName == null ? null : belongDeptName.trim();
    }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate == null ? null : delegate.trim();
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo == null ? null : creditNo.trim();
    }

    public Date getZjxRecordDate() {
        return zjxRecordDate;
    }

    public void setZjxRecordDate(Date zjxRecordDate) {
        this.zjxRecordDate = zjxRecordDate;
    }

    public String getZjxRecordNo() {
        return zjxRecordNo;
    }

    public void setZjxRecordNo(String zjxRecordNo) {
        this.zjxRecordNo = zjxRecordNo == null ? null : zjxRecordNo.trim();
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}