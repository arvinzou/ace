package com.huacainfo.ace.society.model;

import java.util.Date;

public class Circle implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String id;

    private String corpId;

    private String uid;

    private Integer sort;

    private String mediaType;

    private Date createTime;

    private String status;

    private Long likeNum;

    private String lastAuditLogId;

    private String content;

    private String mediaContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public String getLastAuditLogId() {
        return lastAuditLogId;
    }

    public void setLastAuditLogId(String lastAuditLogId) {
        this.lastAuditLogId = lastAuditLogId == null ? null : lastAuditLogId.trim();
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent == null ? null : mediaContent.trim();
    }
}