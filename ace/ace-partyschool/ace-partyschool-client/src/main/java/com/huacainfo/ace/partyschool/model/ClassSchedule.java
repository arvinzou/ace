package com.huacainfo.ace.partyschool.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassSchedule  implements Serializable {
    private  static final long serialVersionUID = 1L;
    private String id;

    private String classesId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date courseDate;

    private String courseIndex;

    private String teacherId;

    private String courseId;

    /*1、*/
    private String ifTest;

    public String getIfTest() {
        return ifTest;
    }

    public void setIfTest(String ifTest) {
        this.ifTest = ifTest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex == null ? null : courseIndex.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }
}