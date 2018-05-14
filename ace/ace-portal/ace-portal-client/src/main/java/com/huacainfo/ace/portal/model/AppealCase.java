package com.huacainfo.ace.portal.model;

import java.math.BigDecimal;
import java.util.Date;

public class AppealCase implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String appealId;

    private String category;

    private String title;

    private String mediType;

    private String mediUrl;

    private Date submitTime;

    private String submitOpenId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Date answerTime;

    private String answerOpenId;

    private String content;

    private String answerContent;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppealId() {
        return appealId;
    }

    public void setAppealId(String appealId) {
        this.appealId = appealId == null ? null : appealId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMediType() {
        return mediType;
    }

    public void setMediType(String mediType) {
        this.mediType = mediType == null ? null : mediType.trim();
    }

    public String getMediUrl() {
        return mediUrl;
    }

    public void setMediUrl(String mediUrl) {
        this.mediUrl = mediUrl == null ? null : mediUrl.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitOpenId() {
        return submitOpenId;
    }

    public void setSubmitOpenId(String submitOpenId) {
        this.submitOpenId = submitOpenId == null ? null : submitOpenId.trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerOpenId() {
        return answerOpenId;
    }

    public void setAnswerOpenId(String answerOpenId) {
        this.answerOpenId = answerOpenId == null ? null : answerOpenId.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}