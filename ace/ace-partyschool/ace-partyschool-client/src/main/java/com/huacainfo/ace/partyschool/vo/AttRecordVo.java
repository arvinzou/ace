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

    private String amIn;//上午签到   ps:教职工有此项
    private String amOut;//上午签退
    private String pmIn;//下午签到
    private String pmOut;//下午签退 ps:教职工有此项
    private String nIn;//晚上签到

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

    public String getAmIn() {
        return amIn;
    }

    public void setAmIn(String amIn) {
        this.amIn = amIn;
    }

    public String getAmOut() {
        return amOut;
    }

    public void setAmOut(String amOut) {
        this.amOut = amOut;
    }

    public String getPmIn() {
        return pmIn;
    }

    public void setPmIn(String pmIn) {
        this.pmIn = pmIn;
    }

    public String getPmOut() {
        return pmOut;
    }

    public void setPmOut(String pmOut) {
        this.pmOut = pmOut;
    }

    public String getnIn() {
        return nIn;
    }

    public void setnIn(String nIn) {
        this.nIn = nIn;
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
