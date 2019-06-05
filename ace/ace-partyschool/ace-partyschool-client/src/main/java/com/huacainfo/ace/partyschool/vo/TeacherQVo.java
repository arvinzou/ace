package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Teacher;


public class TeacherQVo extends Teacher {
    private static final long serialVersionUID = 1L;

    private String keyWord;
    /***
     * 用于考勤数据，筛选掉 “临时机构”处室的教职工
     */
    private String whole;

    public String getWhole() {
        return whole;
    }

    public void setWhole(String whole) {
        this.whole = whole;
    }

    public String getKeyWord() {
        return keyWord == null ? null : keyWord.trim();
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
