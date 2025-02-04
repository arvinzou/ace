package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Course;


public class CourseVo extends Course {
    private static final long serialVersionUID = 1L;
    private String evaluatingName;
    private String teacherName;
    private String codeName;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

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
