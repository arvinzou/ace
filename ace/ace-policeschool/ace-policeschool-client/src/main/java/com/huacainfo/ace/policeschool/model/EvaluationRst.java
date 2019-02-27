package com.huacainfo.ace.policeschool.model;

import java.io.Serializable;
import java.util.Date;

public class EvaluationRst implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatingId;

    private String name;

    private String classScheduleId;

    private String introduce;

    private Integer score;

    private String userId;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvaluatingId() {
        return evaluatingId;
    }

    public void setEvaluatingId(String evaluatingId) {
        this.evaluatingId = evaluatingId == null ? null : evaluatingId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(String classScheduleId) {
        this.classScheduleId = classScheduleId == null ? null : classScheduleId.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}