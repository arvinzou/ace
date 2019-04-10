package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class ErrFeedback extends BaseModel {
    private String id;

    private String deviceCode;

    private String errType;

    private String errLevel;

    private Date errDate;

    private String errContent;

    private Integer errLoopNum;

    private String dataSrcCode;

    private String apiRst;

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

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType == null ? null : errType.trim();
    }

    public String getErrLevel() {
        return errLevel;
    }

    public void setErrLevel(String errLevel) {
        this.errLevel = errLevel == null ? null : errLevel.trim();
    }

    public Date getErrDate() {
        return errDate;
    }

    public void setErrDate(Date errDate) {
        this.errDate = errDate;
    }

    public String getErrContent() {
        return errContent;
    }

    public void setErrContent(String errContent) {
        this.errContent = errContent == null ? null : errContent.trim();
    }

    public Integer getErrLoopNum() {
        return errLoopNum;
    }

    public void setErrLoopNum(Integer errLoopNum) {
        this.errLoopNum = errLoopNum;
    }

    public String getDataSrcCode() {
        return dataSrcCode;
    }

    public void setDataSrcCode(String dataSrcCode) {
        this.dataSrcCode = dataSrcCode == null ? null : dataSrcCode.trim();
    }

    public String getApiRst() {
        return apiRst;
    }

    public void setApiRst(String apiRst) {
        this.apiRst = apiRst == null ? null : apiRst.trim();
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
