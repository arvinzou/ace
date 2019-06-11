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
    /**
     * 测评人数
     */
    private int testNum;
    /**
     * 已测评人数
     */
    private int testedNum;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 教官姓名
     */
    private String teacherName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    public int getTestedNum() {
        return testedNum;
    }

    public void setTestedNum(int testedNum) {
        this.testedNum = testedNum;
    }

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
