package com.huacainfo.ace.policeschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class AttRecord extends BaseModel {
    private String id;

    private String userId;

    private String userType;

    private Date attTime;

    private String attPhoto;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String status;

    private String remark;

    private Date createDate;

    private String attTimeStr;

    public String getAttTimeStr() {
        return attTimeStr;
    }

    public void setAttTimeStr(String attTimeStr) {
        this.attTimeStr = attTimeStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Date getAttTime() {
        return attTime;
    }

    public void setAttTime(Date attTime) {
        this.attTime = attTime;
    }

    public String getAttPhoto() {
        return attPhoto;
    }

    public void setAttPhoto(String attPhoto) {
        this.attPhoto = attPhoto == null ? null : attPhoto.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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