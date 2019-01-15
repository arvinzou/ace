package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.model.Teacher;


public class ClassScheduleVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;

    private Course course;

    private Teacher teacher;

    private int userNum;

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    private int studentNum;

    private int averageScore;

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
