package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Counselor;


public class CounselorQVo extends Counselor {
    private static final long serialVersionUID = 1L;

    /**
     * 注册审核状态  0-待审核 1-审核通过 2-审核拒绝
     */
    private String regAuditRst;
    /**
     * 咨询方式 demo：1,2,3
     */
    private String consultType;
    /**
     * 咨询方式数组
     */
    private String[] consultTypeArray;

    public String getConsultType() {
        return consultType;
    }

    public void setConsultType(String consultType) {
        this.consultType = consultType;
    }

    public String[] getConsultTypeArray() {
        return consultTypeArray;
    }

    public void setConsultTypeArray(String[] consultTypeArray) {
        this.consultTypeArray = consultTypeArray;
    }

    public String getRegAuditRst() {
        return regAuditRst;
    }

    public void setRegAuditRst(String regAuditRst) {
        this.regAuditRst = regAuditRst;
    }
}
