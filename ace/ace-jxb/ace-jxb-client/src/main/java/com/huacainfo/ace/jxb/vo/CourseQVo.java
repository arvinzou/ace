package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Course;


public class CourseQVo extends Course {
    private static final long serialVersionUID = 1L;

    /**
     * 课程审核结果  0-待审核 1-审核通过 2-审核不通过
     */
    private String auditRst;

    /**
     * 工作室ID
     */
    private String studioId;

    public String getStudioId() {
        return studioId;
    }

    public void setStudioId(String studioId) {
        this.studioId = studioId;
    }

    public String getAuditRst() {
        return auditRst;
    }

    public void setAuditRst(String auditRst) {
        this.auditRst = auditRst;
    }
}
