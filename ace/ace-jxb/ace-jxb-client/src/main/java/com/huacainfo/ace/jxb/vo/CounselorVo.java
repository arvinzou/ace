package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Counselor;


public class CounselorVo extends Counselor {
    private static final long serialVersionUID = 1L;

    /**
     * 工作室名称（推荐人名称）
     */
    private String studioName;
    /**
     * 注册资格审核结果 0-待审核 1-审核通过 2-审核拒绝
     */
    private String regAuditRst;


    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getRegAuditRst() {
        return regAuditRst;
    }

    public void setRegAuditRst(String regAuditRst) {
        this.regAuditRst = regAuditRst;
    }
}
