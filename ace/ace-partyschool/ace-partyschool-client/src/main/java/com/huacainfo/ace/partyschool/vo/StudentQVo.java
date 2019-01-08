package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Student;


public class StudentQVo extends Student {
    private static final long serialVersionUID = 1L;
    /**
     * 班级名称
     */
    private String clsName;

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
}
