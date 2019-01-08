package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.model.Teacher;


public class ClassScheduleVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;

    private Course course;

    private Teacher teacher;


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
