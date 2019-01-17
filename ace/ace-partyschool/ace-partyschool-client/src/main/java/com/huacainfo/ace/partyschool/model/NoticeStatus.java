package com.huacainfo.ace.partyschool.model;

import java.io.Serializable;
import java.util.Date;

public class NoticeStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String noticeId;

    private String userId;

    /**
     * 2、已读
     * 1、未读
     * 0、删除
     */
    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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