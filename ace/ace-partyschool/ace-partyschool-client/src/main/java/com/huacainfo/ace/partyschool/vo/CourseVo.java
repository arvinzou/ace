package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Course;


public class CourseVo extends Course {
private static final long serialVersionUID = 1L;
private  String evaluatingName;
private String teacherName;

    public String getEvaluatingName() {
        return evaluatingName;
    }

    public void setEvaluatingName(String evaluatingName) {
        this.evaluatingName = evaluatingName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
