package com.huacainfo.ace.portal.model;

import java.util.Date;

public class WxFormid implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String formId;
    private String openId;

    private Long expire;

    private String status;

    private Date createDate;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}