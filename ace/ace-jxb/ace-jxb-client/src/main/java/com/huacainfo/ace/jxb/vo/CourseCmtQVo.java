package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CourseCmt;


public class CourseCmtQVo extends CourseCmt {
    private static final long serialVersionUID = 1L;

    private String nickname;
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
