package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class AnimaLnk extends BaseModel{
    private String id;

    private String aiCode;

    private String lnkCode;

    private String lnkType;

    private String prePlayUrl;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAiCode() {
        return aiCode;
    }

    public void setAiCode(String aiCode) {
        this.aiCode = aiCode == null ? null : aiCode.trim();
    }

    public String getLnkCode() {
        return lnkCode;
    }

    public void setLnkCode(String lnkCode) {
        this.lnkCode = lnkCode == null ? null : lnkCode.trim();
    }

    public String getLnkType() {
        return lnkType;
    }

    public void setLnkType(String lnkType) {
        this.lnkType = lnkType == null ? null : lnkType.trim();
    }

    public String getPrePlayUrl() {
        return prePlayUrl;
    }

    public void setPrePlayUrl(String prePlayUrl) {
        this.prePlayUrl = prePlayUrl == null ? null : prePlayUrl.trim();
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