package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class SeNodeMeter extends BaseModel {
    private String id;

    private int nodeID;

    private String meterID;

    private BigDecimal meterValue;

    private String meterValueUnit;

    private String updateTime;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID == null ? null : meterID.trim();
    }

    public BigDecimal getMeterValue() {
        return meterValue;
    }

    public void setMeterValue(BigDecimal meterValue) {
        this.meterValue = meterValue;
    }

    public String getMeterValueUnit() {
        return meterValueUnit;
    }

    public void setMeterValueUnit(String meterValueUnit) {
        this.meterValueUnit = meterValueUnit == null ? null : meterValueUnit.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
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
