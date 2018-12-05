package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CounselorPostLevel;

/**
 * @Auther: Arvin
 * @Date: 2018/8/24 15:59
 * @Description:
 */
public class CounselorPostLevelQVo extends CounselorPostLevel {

    /**
     * 咨询师姓名
     */
    private String teacherName;
    /**
     * 季度
     */
    private String quarter;
    /**
     * 年份
     */
    private String year;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
