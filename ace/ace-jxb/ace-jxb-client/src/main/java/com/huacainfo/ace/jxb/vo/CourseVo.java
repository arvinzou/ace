package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.model.CourseSource;


public class CourseVo extends Course {
    private static final long serialVersionUID = 1L;

    /**
     * 课程资源名称
     */
    private String srcName;
    /**
     * 课程资源地址
     */
    private String mediUrl;
    /**
     * 课程资源时长
     */
    private Integer srcDuration;
    /**
     * 是否免费 0-否，1-是
     */
    private String free;

    /**
     * 课程审核结果  0-待审核 1-审核通过 2-审核不通过
     */
    private String auditRst;

    /**
     * 单课程 直接加载单个课程资源
     */
    private CourseSource courseSource;

    /**
     * 咨询师信息
     */
    private CounselorVo counselor;

    /**
     * 咨询师名称
     */
    private String counselorName;

    /**
     * 课程下，所有资源数量
     */
    private Integer srcCount;
    /**
     * 审核备注
     */
    private String auditRemark;

    public Integer getSrcCount() {
        return srcCount;
    }

    public void setSrcCount(Integer srcCount) {
        this.srcCount = srcCount;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public CounselorVo getCounselor() {
        return counselor;
    }

    public void setCounselor(CounselorVo counselor) {
        this.counselor = counselor;
    }

    public String getAuditRst() {
        return auditRst;
    }

    public void setAuditRst(String auditRst) {
        this.auditRst = auditRst;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getMediUrl() {
        return mediUrl;
    }

    public void setMediUrl(String mediUrl) {
        this.mediUrl = mediUrl;
    }

    public Integer getSrcDuration() {
        return srcDuration;
    }

    public void setSrcDuration(Integer srcDuration) {
        this.srcDuration = srcDuration;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public CourseSource getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(CourseSource courseSource) {
        this.courseSource = courseSource;
    }
}
