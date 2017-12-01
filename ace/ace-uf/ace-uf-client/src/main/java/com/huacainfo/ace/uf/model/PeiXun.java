package com.huacainfo.ace.uf.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Demo class
 *
 * @author wn
 * @date 2017/11/28
 */

public class PeiXun {
    private String id;

    private String name;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String dept;

    private String trainees;

    private String category;

    private Date time;

    private String content;

    private String cover;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String peixunId) {
        this.id = peixunId == null ? null : peixunId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
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

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getTrainees() {
        return trainees;
    }

    public void setTrainees(String trainees) {
        this.trainees = trainees == null ? null : trainees.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "PeiXun{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dept='" + dept + '\'' +
                ", trainees='" + trainees + '\'' +
                ", category='" + category + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", createDate=" + createDate +
                ", lastModifyUserId='" + lastModifyUserId + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", lastModifyDate=" + lastModifyDate +
                '}';
    }
}