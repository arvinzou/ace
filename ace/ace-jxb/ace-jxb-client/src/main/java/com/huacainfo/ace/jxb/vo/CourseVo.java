package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Course;
import com.huacainfo.ace.jxb.model.CourseSource;


public class CourseVo extends Course {
    private static final long serialVersionUID = 1L;

    /**
     * 单课程 直接加载单个课程资源
     */
    private CourseSource courseSource;

    public CourseSource getCourseSource() {
        return courseSource;
    }

    public void setCourseSource(CourseSource courseSource) {
        this.courseSource = courseSource;
    }
}
