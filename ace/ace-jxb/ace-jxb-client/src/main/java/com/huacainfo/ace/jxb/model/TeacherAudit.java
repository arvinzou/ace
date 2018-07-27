package com.huacainfo.ace.jxb.model;

import java.util.Date;

public class TeacherAudit {
    private String id;

    private String counselorId;

    private String auditor;

    private String statement;

    /**
     * 审核结果 0-待审核 1-审核通过 2-审核不通过
     */
    private String rst;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId == null ? null : counselorId.trim();
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor == null ? null : auditor.trim();
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement == null ? null : statement.trim();
    }

    public String getRst() {
        return rst;
    }

    public void setRst(String rst) {
        this.rst = rst == null ? null : rst.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}