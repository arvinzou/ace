package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @Auther: Arvin
 * @Date: 2018/9/26 09:40
 * @Description:
 */
public class IncomeReportQVo extends BaseModel {
    /**
     * 季节 1-第一季度，2-第二季度，3-第三季度，4-第四季度
     */
    private String season;
    /**
     * 查询时间  demo:2018-08-06 09:44:44
     */
    private String dateTime;

    /**
     * 老师姓名
     */
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
