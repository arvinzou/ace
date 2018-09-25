package com.huacainfo.ace.society.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class Activity extends BaseModel {

    private String id;

    private String initiatorId;

    private String title;

    private String category;

    private String summary;

    private String purpose;

    private String location;

    private String coverUrl;

    private String startSignImgUrl;

    private String endSignImgUrl;

    private Date dendline;

    private Date startDate;

    private Date endDate;

    private Integer volunteerNum;

    private Integer volunteerPoints;

    private Integer parterNum;

    private Integer parterPoints;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    private String content;

    private String coinconfigId;

    public String getCoinconfigId() {
        return coinconfigId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCoinconfigId(String coinconfigId) {
        this.coinconfigId = coinconfigId;
    }

    public String getStartSignImgUrl() {
        return startSignImgUrl;
    }

    public void setStartSignImgUrl(String startSignImgUrl) {
        this.startSignImgUrl = startSignImgUrl;
    }

    public String getEndSignImgUrl() {
        return endSignImgUrl;
    }

    public void setEndSignImgUrl(String endSignImgUrl) {
        this.endSignImgUrl = endSignImgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId == null ? null : initiatorId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    public Date getDendline() {
        return dendline;
    }

    public void setDendline(Date dendline) {
        this.dendline = dendline;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getVolunteerNum() {
        return volunteerNum;
    }

    public void setVolunteerNum(Integer volunteerNum) {
        this.volunteerNum = volunteerNum;
    }

    public Integer getVolunteerPoints() {
        return volunteerPoints;
    }

    public void setVolunteerPoints(Integer volunteerPoints) {
        this.volunteerPoints = volunteerPoints;
    }

    public Integer getParterNum() {
        return parterNum;
    }

    public void setParterNum(Integer parterNum) {
        this.parterNum = parterNum;
    }

    public Integer getParterPoints() {
        return parterPoints;
    }

    public void setParterPoints(Integer parterPoints) {
        this.parterPoints = parterPoints;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}