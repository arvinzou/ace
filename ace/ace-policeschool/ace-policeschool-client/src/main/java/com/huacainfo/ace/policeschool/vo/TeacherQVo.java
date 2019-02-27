package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Teacher;


public class TeacherQVo extends Teacher {
    private static final long serialVersionUID = 1L;

    private String keyWord;

    public String getKeyWord() {
        return keyWord == null ? null : keyWord.trim();
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
