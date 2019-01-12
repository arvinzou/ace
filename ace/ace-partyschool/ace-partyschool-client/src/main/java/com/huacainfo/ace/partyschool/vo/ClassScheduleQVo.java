package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ClassSchedule;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ClassScheduleQVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;
    private String weekDate;
    private String cName;

    private String cTeacherId;

    private String courseDateStr;



    /**
     * 判断用
     */
    private String learned;

    public String getCourseDateStr() {
        return courseDateStr;
    }

    public void setCourseDateStr(String courseDateStr) {
        this.courseDateStr = courseDateStr;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(String weekDate) {
        this.weekDate = weekDate;
    }

    public String getLearned() {
        return learned;
    }

    public void setLearned(String learned) {
        this.learned = learned;
    }

    public String getcTeacherId() {
        return cTeacherId;
    }

    public void setcTeacherId(String cTeacherId) {
        this.cTeacherId = cTeacherId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
