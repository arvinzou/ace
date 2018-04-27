package com.huacainfo.ace.woc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HuaCai003
 */
public class Site implements Serializable {

    private static final long serialVersionUID = 5033912890085381733L;

    private String id;

    private String siteName;

    private String siteCode;

    private String areaCode;

    private Integer roadwayNum;

    private Integer checkpointsNum;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String address;

    private Date constructDate;

    private String roadId;

    private String adminDepId;

    private String sitStatus;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    private String buildDeptId;

    /**
     * 数据统计时使用
     */
    private int count;

    private int illegalCount;


    public int getIllegalCount() {
        return illegalCount;
    }

    public void setIllegalCount(int illegalCount) {
        this.illegalCount = illegalCount;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBuildDeptId() {
        return buildDeptId;
    }

    public void setBuildDeptId(String buildDeptId) {
        this.buildDeptId = buildDeptId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Integer getRoadwayNum() {
        return roadwayNum;
    }

    public void setRoadwayNum(Integer roadwayNum) {
        this.roadwayNum = roadwayNum;
    }

    public Integer getCheckpointsNum() {
        return checkpointsNum;
    }

    public void setCheckpointsNum(Integer checkpointsNum) {
        this.checkpointsNum = checkpointsNum;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId == null ? null : roadId.trim();
    }

    public String getAdminDepId() {
        return adminDepId;
    }

    public void setAdminDepId(String adminDepId) {
        this.adminDepId = adminDepId == null ? null : adminDepId.trim();
    }

    public String getSitStatus() {
        return sitStatus;
    }

    public void setSitStatus(String sitStatus) {
        this.sitStatus = sitStatus == null ? null : sitStatus.trim();
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