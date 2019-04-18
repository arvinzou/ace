package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SePresetData extends BaseModel {
    private String id;

    private Integer presetNo;

    private String presetName;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getPresetNo() {
        return presetNo;
    }

    public void setPresetNo(Integer presetNo) {
        this.presetNo = presetNo;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName == null ? null : presetName.trim();
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