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
     * 单课程 直接加载单个课程资源
     */
    private CourseSource courseSource;

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
