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
