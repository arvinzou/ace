package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CourseCmt;


public class CourseCmtQVo extends CourseCmt {
    private static final long serialVersionUID = 1L;

    /**
     * 评论人名称
     */
    private String nickname;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 咨询师ID
     */
    private String counselorId;

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId;
    }

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
