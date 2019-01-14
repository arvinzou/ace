package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Classes;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.model.Teacher;


public class ClassesVo extends Classes {
    private static final long serialVersionUID = 1L;
    private Classroom classroom;

    private Teacher teacher;

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
