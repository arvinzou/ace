package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.util.Date;

public class CourseAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String courseId;

    private String auditor;

    private String statement;

    private String rst;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
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