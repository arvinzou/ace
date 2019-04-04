package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Student;


public class StudentQVo extends Student {
    private static final long serialVersionUID = 1L;
    /**
     * 班级名称
     */
    private String clsName;

    private String keyWord;

    private String areaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getKeyWord() {
        return keyWord == null ? null : keyWord.trim();
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }
}
