package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Course;

import java.util.List;


public class CourseQVo extends Course {
    private static final long serialVersionUID = 1L;

    private List<String> teacherIds;
    /**
     * 搜索关键词
     */
    private String keyword;
    private String tName;

    public String getKeyword() {
        return keyword;
    }

    public List<String> getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(List<String> teacherIds) {
        this.teacherIds = teacherIds;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
