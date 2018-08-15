package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.util.Date;

public class CourseSource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String courseId;
    /**
     * 0 - 表示无所属章节 其他表示 - course_part.id
     */
    private String partId;

    private String name;

    private String mediUrl;

    private Integer duration;

    /**
     * 视频介绍
     */
    private String introduction;

    /**
     * 是否免费 0-否，1-是
     */
    private String free;

    private Integer displaySeq;

    private Date createDate;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMediUrl() {
        return mediUrl;
    }

    public void setMediUrl(String mediUrl) {
        this.mediUrl = mediUrl == null ? null : mediUrl.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free == null ? null : free.trim();
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}