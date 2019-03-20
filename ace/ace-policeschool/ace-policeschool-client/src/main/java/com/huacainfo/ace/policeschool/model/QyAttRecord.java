package com.huacainfo.ace.policeschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class QyAttRecord extends BaseModel {
    private String attenId;

    private String companyId;

    private String attenDevice;

    private String deviceName;

    private String attenUid;

    private String realname;

    private String departName;

    private String attenType;

    private String attenStatus;

    private Long attenTime;

    private String attenDate;

    private String remark;

    private Date createDate;

    public String getAttenId() {
        return attenId;
    }

    public void setAttenId(String attenId) {
        this.attenId = attenId == null ? null : attenId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getAttenDevice() {
        return attenDevice;
    }

    public void setAttenDevice(String attenDevice) {
        this.attenDevice = attenDevice == null ? null : attenDevice.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getAttenUid() {
        return attenUid;
    }

    public void setAttenUid(String attenUid) {
        this.attenUid = attenUid == null ? null : attenUid.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
    }

    public String getAttenType() {
        return attenType;
    }

    public void setAttenType(String attenType) {
        this.attenType = attenType == null ? null : attenType.trim();
    }

    public String getAttenStatus() {
        return attenStatus;
    }

    public void setAttenStatus(String attenStatus) {
        this.attenStatus = attenStatus == null ? null : attenStatus.trim();
    }

    public Long getAttenTime() {
        return attenTime;
    }

    public void setAttenTime(Long attenTime) {
        this.attenTime = attenTime;
    }

    public String getAttenDate() {
        return attenDate;
    }

    public void setAttenDate(String attenDate) {
        this.attenDate = attenDate == null ? null : attenDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}