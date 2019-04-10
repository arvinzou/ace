package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class AnimaRes extends BaseModel{
    private String id;

    private String code;

    private String name;

    private String type;

    private Integer duration;

    private String playUrl;

    private String prePlayUrl;

    private String preImgUrl;

    private Long rsoWidth;

    private Long rsoHeight;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl == null ? null : playUrl.trim();
    }

    public String getPrePlayUrl() {
        return prePlayUrl;
    }

    public void setPrePlayUrl(String prePlayUrl) {
        this.prePlayUrl = prePlayUrl == null ? null : prePlayUrl.trim();
    }

    public String getPreImgUrl() {
        return preImgUrl;
    }

    public void setPreImgUrl(String preImgUrl) {
        this.preImgUrl = preImgUrl == null ? null : preImgUrl.trim();
    }

    public Long getRsoWidth() {
        return rsoWidth;
    }

    public void setRsoWidth(Long rsoWidth) {
        this.rsoWidth = rsoWidth;
    }

    public Long getRsoHeight() {
        return rsoHeight;
    }

    public void setRsoHeight(Long rsoHeight) {
        this.rsoHeight = rsoHeight;
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