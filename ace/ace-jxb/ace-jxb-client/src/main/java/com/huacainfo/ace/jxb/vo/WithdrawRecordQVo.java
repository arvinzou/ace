package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.WithdrawRecord;


public class WithdrawRecordQVo extends WithdrawRecord {
    private static final long serialVersionUID = 1L;
    /**
     * 审核状态值 ：  temp-审核中 pass-审核通过 reject-审核失败
     */
    private String[] auditRstArray;
    /**
     * 当月时间字符串
     */
    private String montDateStr;

    public String getMontDateStr() {
        return montDateStr;
    }

    public void setMontDateStr(String montDateStr) {
        this.montDateStr = montDateStr;
    }

    public String[] getAuditRstArray() {
        return auditRstArray;
    }

    public void setAuditRstArray(String[] auditRstArray) {
        this.auditRstArray = auditRstArray;
    }
}
