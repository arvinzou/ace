package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.AttRecord;


public class AttRecordVo extends AttRecord {
    private static final long serialVersionUID = 1L;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户类型名称
     */
    private String userTypeName;

    private String attenDateTime;


    private String userId;

    private String classId;

    private String attDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getAttenDateTime() {
        return attenDateTime;
    }

    public void setAttenDateTime(String attenDateTime) {
        this.attenDateTime = attenDateTime;
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

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }
}
