package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SceneExecutor extends BaseModel {
    private String id;

    private String loopKey;

    private String sceneCfgId;

    private String remark;

    private String status;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoopKey() {
        return loopKey;
    }

    public void setLoopKey(String loopKey) {
        this.loopKey = loopKey == null ? null : loopKey.trim();
    }

    public String getSceneCfgId() {
        return sceneCfgId;
    }

    public void setSceneCfgId(String sceneCfgId) {
        this.sceneCfgId = sceneCfgId == null ? null : sceneCfgId.trim();
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