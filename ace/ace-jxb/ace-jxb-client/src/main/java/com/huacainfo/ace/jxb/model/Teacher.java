package com.huacainfo.ace.jxb.model;

import java.math.BigDecimal;
import java.util.Date;

public class Teacher implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String mobile;

    private String imagePhotoUrl;

    private String visitCardUrl;

    private String idCard;

    private String idCardImgUrl;

    private String certificateNo;

    private String certificateImgUrl;

    private String evidenceImgUrl;

    private BigDecimal income;

    private BigDecimal account;

    private String level;

    private Date createDate;

    private String profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getImagePhotoUrl() {
        return imagePhotoUrl;
    }

    public void setImagePhotoUrl(String imagePhotoUrl) {
        this.imagePhotoUrl = imagePhotoUrl == null ? null : imagePhotoUrl.trim();
    }

    public String getVisitCardUrl() {
        return visitCardUrl;
    }

    public void setVisitCardUrl(String visitCardUrl) {
        this.visitCardUrl = visitCardUrl == null ? null : visitCardUrl.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdCardImgUrl() {
        return idCardImgUrl;
    }

    public void setIdCardImgUrl(String idCardImgUrl) {
        this.idCardImgUrl = idCardImgUrl == null ? null : idCardImgUrl.trim();
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo == null ? null : certificateNo.trim();
    }

    public String getCertificateImgUrl() {
        return certificateImgUrl;
    }

    public void setCertificateImgUrl(String certificateImgUrl) {
        this.certificateImgUrl = certificateImgUrl == null ? null : certificateImgUrl.trim();
    }

    public String getEvidenceImgUrl() {
        return evidenceImgUrl;
    }

    public void setEvidenceImgUrl(String evidenceImgUrl) {
        this.evidenceImgUrl = evidenceImgUrl == null ? null : evidenceImgUrl.trim();
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }
}