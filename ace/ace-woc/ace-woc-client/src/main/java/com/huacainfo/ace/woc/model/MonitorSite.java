package com.huacainfo.ace.woc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HuaCai003
 */
public class MonitorSite implements Serializable {
    private static final long serialVersionUID = -1387251659853030705L;
    private String id;

    private String siteId;

    private String monitorSiteName;

    private String monitorSiteNo;

    private String monitorSiteStatus;

    private String areaCode;

    private String direction;

    private BigDecimal latitude;

    private BigDecimal longitude;

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

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getMonitorSiteName() {
        return monitorSiteName;
    }

    public void setMonitorSiteName(String monitorSiteName) {
        this.monitorSiteName = monitorSiteName == null ? null : monitorSiteName.trim();
    }

    public String getMonitorSiteNo() {
        return monitorSiteNo;
    }

    public void setMonitorSiteNo(String monitorSiteNo) {
        this.monitorSiteNo = monitorSiteNo == null ? null : monitorSiteNo.trim();
    }

    public String getMonitorSiteStatus() {
        return monitorSiteStatus;
    }

    public void setMonitorSiteStatus(String monitorSiteStatus) {
        this.monitorSiteStatus = monitorSiteStatus == null ? null : monitorSiteStatus.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
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