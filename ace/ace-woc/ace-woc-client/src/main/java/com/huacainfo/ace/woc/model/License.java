package com.huacainfo.ace.woc.model;

import java.io.Serializable;
import java.util.Date;

public class License implements Serializable{
    private static final long serialVersionUID = 2648618122780760299L;
    private String id;
    /**
     * 1: '身份证', 2: '驾驶证', 3: '行驶证', 4: '营业证', 5: '交通运输许可证'
     */
    private String licenseType;

    private String licenseNo;

    private Date expiryDate;

    private String personId;

    private String vehicleId;

    private String departmentId;

    private String licenseImg1;

    private String licenseImg2;

    private String licenseImg3;

    private String licenseImg4;

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

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType == null ? null : licenseType.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId == null ? null : vehicleId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getLicenseImg1() {
        return licenseImg1;
    }

    public void setLicenseImg1(String licenseImg1) {
        this.licenseImg1 = licenseImg1 == null ? null : licenseImg1.trim();
    }

    public String getLicenseImg2() {
        return licenseImg2;
    }

    public void setLicenseImg2(String licenseImg2) {
        this.licenseImg2 = licenseImg2 == null ? null : licenseImg2.trim();
    }

    public String getLicenseImg3() {
        return licenseImg3;
    }

    public void setLicenseImg3(String licenseImg3) {
        this.licenseImg3 = licenseImg3 == null ? null : licenseImg3.trim();
    }

    public String getLicenseImg4() {
        return licenseImg4;
    }

    public void setLicenseImg4(String licenseImg4) {
        this.licenseImg4 = licenseImg4 == null ? null : licenseImg4.trim();
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