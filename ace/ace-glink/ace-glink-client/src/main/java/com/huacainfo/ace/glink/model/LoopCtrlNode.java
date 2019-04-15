package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class LoopCtrlNode extends BaseModel {
    private String id;

    private String nodeCode;

    private String loopName;

    private String loopKey;

    private String loopType;

    private String loopCurrent;

    private Integer state;

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

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode == null ? null : nodeCode.trim();
    }

    public String getLoopName() {
        return loopName;
    }

    public void setLoopName(String loopName) {
        this.loopName = loopName == null ? null : loopName.trim();
    }

    public String getLoopKey() {
        return loopKey;
    }

    public void setLoopKey(String loopKey) {
        this.loopKey = loopKey == null ? null : loopKey.trim();
    }

    public String getLoopType() {
        return loopType;
    }

    public void setLoopType(String loopType) {
        this.loopType = loopType == null ? null : loopType.trim();
    }

    public String getLoopCurrent() {
        return loopCurrent;
    }

    public void setLoopCurrent(String loopCurrent) {
        this.loopCurrent = loopCurrent == null ? null : loopCurrent.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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