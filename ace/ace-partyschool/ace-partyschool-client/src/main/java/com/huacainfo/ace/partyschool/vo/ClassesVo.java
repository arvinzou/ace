package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.model.Teacher;
import com.huacainfo.ace.partyschool.model.Student;


public class ClassesVo extends Classes {
    private static final long serialVersionUID = 1L;
    private Classroom classroom;
    /**
     班主任信息
     */
    private Teacher teacher;

    /**
     跟班老师信息
     */
    private  Teacher clsTeacher;

    /**
     跟班干部信息
     */
    private Student student;

    private String crName;

    private String tName;

    private String t1Name;

    private String sName;

    public String getT1Name() {
        return t1Name;
    }

    public void setT1Name(String t1Name) {
        this.t1Name = t1Name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getCrName() {
        return crName;
    }

    public void setCrName(String crName) {
        this.crName = crName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Teacher getClsteacher() {
        return clsTeacher;
    }

    public void setClsteacher(Teacher clsTeacher) {
        this.clsTeacher = clsTeacher;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
