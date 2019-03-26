package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.AttRecord;


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

    private String clsId;

    private String clsName;
    /**
     * 年月日
     */
    private String attDate;

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getClsId() {
        return clsId;
    }

    public void setClsId(String clsId) {
        this.clsId = clsId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

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
}
