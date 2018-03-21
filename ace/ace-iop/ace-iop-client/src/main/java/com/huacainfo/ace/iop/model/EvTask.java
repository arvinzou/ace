package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTask implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String evTaskId;

    private String category;

    private String evTaskName;

    private String evObj;

    private String evTempleteId;

    private Date evDeadline;

    private Date evStartDate;

    private String status;

    private String admin;

    private Date createTime;
    private String evDiscribtion;

    private String evContent;
    private String deptId;
    public String getEvTaskId() {
        return evTaskId;
    }

    public void setEvTaskId(String evTaskId) {
        this.evTaskId = evTaskId == null ? null : evTaskId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getEvTaskName() {
        return evTaskName;
    }

    public void setEvTaskName(String evTaskName) {
        this.evTaskName = evTaskName == null ? null : evTaskName.trim();
    }

    public String getEvObj() {
        return evObj;
    }

    public void setEvObj(String evObj) {
        this.evObj = evObj == null ? null : evObj.trim();
    }

    public String getEvTempleteId() {
        return evTempleteId;
    }

    public void setEvTempleteId(String evTempleteId) {
        this.evTempleteId = evTempleteId == null ? null : evTempleteId.trim();
    }

    public Date getEvDeadline() {
        return evDeadline;
    }

    public void setEvDeadline(Date evDeadline) {
        this.evDeadline = evDeadline;
    }

    public Date getEvStartDate() {
        return evStartDate;
    }

    public void setEvStartDate(Date evStartDate) {
        this.evStartDate = evStartDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin == null ? null : admin.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getEvDiscribtion() {
		return evDiscribtion;
	}

	public void setEvDiscribtion(String evDiscribtion) {
		this.evDiscribtion = evDiscribtion;
	}

	public String getEvContent() {
		return evContent;
	}

	public void setEvContent(String evContent) {
		this.evContent = evContent;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
    
}