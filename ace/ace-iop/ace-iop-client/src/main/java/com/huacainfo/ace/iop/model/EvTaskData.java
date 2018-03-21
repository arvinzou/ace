package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTaskData implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String evTaskDataId;

    private String evTaskId;

    private String evUserId;
    
    private String userId;

    private String evTarget;

    private String evScoreId;

    private String status;

    private Date createTime;

    private String remark;

    public String getEvTaskDataId() {
        return evTaskDataId;
    }

    public void setEvTaskDataId(String evTaskDataId) {
        this.evTaskDataId = evTaskDataId == null ? null : evTaskDataId.trim();
    }

    public String getEvTaskId() {
        return evTaskId;
    }

    public void setEvTaskId(String evTaskId) {
        this.evTaskId = evTaskId == null ? null : evTaskId.trim();
    }

    public String getEvUserId() {
        return evUserId;
    }

    public void setEvUserId(String evUserId) {
        this.evUserId = evUserId == null ? null : evUserId.trim();
    }

    public String getEvTarget() {
        return evTarget;
    }

    public void setEvTarget(String evTarget) {
        this.evTarget = evTarget == null ? null : evTarget.trim();
    }

    public String getEvScoreId() {
        return evScoreId;
    }

    public void setEvScoreId(String evScoreId) {
        this.evScoreId = evScoreId == null ? null : evScoreId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}