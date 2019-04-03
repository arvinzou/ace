package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.ClassSchedule;

import java.util.List;


public class ClassScheduleQVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;
    private String weekDate;
    private String cName;

    private String cTeacherId;

    private String startTimeStr;

    private List<String> classList;

    private String erUserId;

    /**
     * 判断用
     */
    private String learned;

    public String getErUserId() {
        return erUserId;
    }

    public void setErUserId(String erUserId) {
        this.erUserId = erUserId;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }


    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
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
