package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Course;

import java.util.List;


public class CourseVo extends Course {
private static final long serialVersionUID = 1L;
private  String evaluatingName;
private String teacherName;
private String teacherNames;
private String codeName;

    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    private List<CourseTeacherVo> courseTeacherVoList;

    public List<CourseTeacherVo> getCourseTeacherVoList() {
        return courseTeacherVoList;
    }

    public void setCourseTeacherVoList(List<CourseTeacherVo> courseTeacherVoList) {
        this.courseTeacherVoList = courseTeacherVoList;
    }

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
