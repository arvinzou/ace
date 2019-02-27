package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Course;


public class CourseQVo extends Course {
    private static final long serialVersionUID = 1L;
    private String tName;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
