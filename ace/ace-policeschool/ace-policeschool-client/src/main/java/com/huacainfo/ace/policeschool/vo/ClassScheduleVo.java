package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.ClassSchedule;
import com.huacainfo.ace.policeschool.model.Classes;
import com.huacainfo.ace.policeschool.model.Course;
import com.huacainfo.ace.policeschool.model.Teacher;


public class ClassScheduleVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;

    private Course course;

    private Teacher teacher;

    private Classes classes;

    private int userNum;
    private int studentNum;
    private int averageScore;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
