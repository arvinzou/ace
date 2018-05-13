package com.huacainfo.ace.portal.model;

import java.io.Serializable;
import java.util.Date;

public class MessageTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String tmplCode;

    /**
     * "0": "text", "1": "xml"
     */
    private String tmplType;

    private String wechatTmplId;

    private String sendWechat;

    private String sendSms;

    private String sysId;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    private String tmplBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTmplCode() {
        return tmplCode;
    }

    public void setTmplCode(String tmplCode) {
        this.tmplCode = tmplCode == null ? null : tmplCode.trim();
    }

    public String getTmplType() {
        return tmplType;
    }

    public void setTmplType(String tmplType) {
        this.tmplType = tmplType == null ? null : tmplType.trim();
    }

    public String getWechatTmplId() {
        return wechatTmplId;
    }

    public void setWechatTmplId(String wechatTmplId) {
        this.wechatTmplId = wechatTmplId == null ? null : wechatTmplId.trim();
    }

    public String getSendWechat() {
        return sendWechat;
    }

    public void setSendWechat(String sendWechat) {
        this.sendWechat = sendWechat == null ? null : sendWechat.trim();
    }

    public String getSendSms() {
        return sendSms;
    }

    public void setSendSms(String sendSms) {
        this.sendSms = sendSms == null ? null : sendSms.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
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

    public String getTmplBody() {
        return tmplBody;
    }

    public void setTmplBody(String tmplBody) {
        this.tmplBody = tmplBody == null ? null : tmplBody.trim();
    }
}