package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Teacher;


public class TeacherVo extends Teacher {
    private static final long serialVersionUID = 1L;

    /**
     * 是否绑定微信注册   0-否 1-是
     */
    private String isBindWx;

    /**
     * 注册账号
     */
    private String signAcct;
    /**
     * 注册密码
     */
    private String singPwd;
    /**
     * 借阅证号
     */
    private String lCardNo;

    public String getlCardNo() {
        return lCardNo;
    }

    public void setlCardNo(String lCardNo) {
        this.lCardNo = lCardNo;
    }

    public String getSignAcct() {
        return signAcct;
    }

    public void setSignAcct(String signAcct) {
        this.signAcct = signAcct;
    }

    public String getSingPwd() {
        return singPwd;
    }

    public void setSingPwd(String singPwd) {
        this.singPwd = singPwd;
    }

    public String getIsBindWx() {
        return isBindWx;
    }

    public void setIsBindWx(String isBindWx) {
        this.isBindWx = isBindWx;
    }
}
