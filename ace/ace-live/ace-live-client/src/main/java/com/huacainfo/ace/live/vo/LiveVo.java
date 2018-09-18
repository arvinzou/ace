
package com.huacainfo.ace.live.vo;

import com.huacainfo.ace.live.model.Live;

import java.util.Date;


public class LiveVo extends Live {
    private static final long serialVersionUID = 1L;

    /**
     * 直播下所有报道数目
     */
    private int reportCount;

    private String auditStatus;

    private String statement;

    private java.util.Date auditDate;

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public String getAuditStatus() {
        return auditStatus;
    }

    @Override
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}
