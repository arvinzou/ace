package com.huacainfo.ace.policeschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class QyCrm extends BaseModel {
    private String id;

    private String userId;

    private String ccNum;

    private String remark;
    /**
     * UNDO        -   未进行同步操作
     * USER_OK     -   员工信息同步成功
     * USER_FAIL   -   员工信息同步失败
     * DEVICE_FAIL -   设备同步失败
     * DEVICE_OK   -   设备同步成功（全流程完）
     */
    private String status;

    private Date createDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCcNum() {
        return ccNum;
    }

    public void setCcNum(String ccNum) {
        this.ccNum = ccNum == null ? null : ccNum.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}