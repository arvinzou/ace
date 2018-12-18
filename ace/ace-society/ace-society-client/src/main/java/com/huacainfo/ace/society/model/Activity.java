package com.huacainfo.ace.society.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class Activity extends BaseModel {

    private String id;

    private String initiatorId;

    private String title;

    /**
     * 1、公益活动，2、普及活动，3创意活动，4、党建活动
     */
    private String category;

    private String mode;

    private String purpose;

    private String location;

    private String coverUrl;

    private String startSignImgUrl;

    private String endSignImgUrl;

    private Date dendline;

    private Date startDate;

    private Date endDate;

    private Date realStartDate;

    private Date realEndDate;

    private Integer volunteerNum;

    private Integer clazz;


    private Integer volunteerPoints;

    private Integer parterNum;

    private Integer parterPoints;

    private String remark;
    /**
     * 1-暂存\\
     * 2-提交审核
     * 3-审核通过
     * 31-活动开始
     * 32-活动结束
     * 33-活动成功
     * 4-审核驳回
     * 41 活动无效
     * 42 活动超时
     */
    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    private String content;

    private String coinconfigId;


    public Date getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(Date realStartDate) {
        this.realStartDate = realStartDate;
    }

    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
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