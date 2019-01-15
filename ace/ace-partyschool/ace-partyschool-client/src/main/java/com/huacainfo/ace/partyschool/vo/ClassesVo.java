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
     跟班干部信息
     */
    private Student student;


    /**
     跟班老师姓名
     */
    private  String tName1;

    public String gettName1() {
        return tName1;
    }

    public void settName1(String tName1) {
        this.tName1 = tName1;
    }

    public String gettMobile1() {
        return tMobile1;
    }

    public void settMobile1(String tMobile1) {
        this.tMobile1 = tMobile1;
    }

    /**
     跟班老师电话
     */
    private  String tMobile1;


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
