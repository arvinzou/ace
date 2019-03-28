package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.AttRecord;


public class AttRecordQVo extends AttRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 查询年月日
     */
    private String dateTimeStr;
    /**
     * 姓名
     */
    private String userName;

    private String startDate;
    private String endDate;

    private String classId;
    private String userId;
    private String nowDate;//"yyyy-MM-dd"
    private String clsName;

    public String getDateTimeStr() {
        return dateTimeStr;
    }

    public void setDateTimeStr(String dateTimeStr) {
        this.dateTimeStr = dateTimeStr;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
}
