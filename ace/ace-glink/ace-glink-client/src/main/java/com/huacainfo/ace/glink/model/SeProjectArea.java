package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeProjectArea extends BaseModel {
    private String id;

    private String pid;

    private String projectName;

    private String areaName;

    private int areaNodeCount;

    private String areaNodeID;

    private int areaType;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public int getAreaNodeCount() {
        return areaNodeCount;
    }

    public void setAreaNodeCount(int areaNodeCount) {
        this.areaNodeCount = areaNodeCount;
    }

    public String getAreaNodeID() {
        return areaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        this.areaNodeID = areaNodeID == null ? null : areaNodeID.trim();
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
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
}