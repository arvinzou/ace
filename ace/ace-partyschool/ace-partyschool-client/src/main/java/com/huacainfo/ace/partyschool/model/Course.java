package com.huacainfo.ace.partyschool.model;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;
    /**
     * 1、现场教学
     * 2、室内教学
     */
    private String category;

    private String teacherId;

    private String teacherId1;

    private String teacherId2;

    private String teacherId3;

    private String teacherId4;

    private String evaluatingId;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    private String status;

    public String getTeacherId1() {
        return teacherId1;
    }

    public void setTeacherId1(String teacherId1) {
        this.teacherId1 = teacherId1;
    }

    public String getTeacherId2() {
        return teacherId2;
    }

    public void setTeacherId2(String teacherId2) {
        this.teacherId2 = teacherId2;
    }

    public String getTeacherId3() {
        return teacherId3;
    }

    public void setTeacherId3(String teacherId3) {
        this.teacherId3 = teacherId3;
    }

    public String getTeacherId4() {
        return teacherId4;
    }

    public void setTeacherId4(String teacherId4) {
        this.teacherId4 = teacherId4;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getEvaluatingId() {
        return evaluatingId;
    }

    public void setEvaluatingId(String evaluatingId) {
        this.evaluatingId = evaluatingId == null ? null : evaluatingId.trim();
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