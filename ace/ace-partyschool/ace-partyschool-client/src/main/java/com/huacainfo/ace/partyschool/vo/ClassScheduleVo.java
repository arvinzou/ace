package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ClassSchedule;
import com.huacainfo.ace.partyschool.model.Course;


public class ClassScheduleVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
