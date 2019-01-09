package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.ClassSchedule;

import javax.xml.crypto.Data;
import java.util.Date;


public class ClassScheduleQVo extends ClassSchedule {
    private static final long serialVersionUID = 1L;
    private Date timePoint;
    private String cName;

    private  String cTeacherId;

    /**判断用*/
    private String learned;


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

    public Date getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(Date timePoint) {
        this.timePoint = timePoint;
    }
}
