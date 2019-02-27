package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.Classes;


public class ClassesQVo extends Classes {
    private static final long serialVersionUID = 1L;

    private String crName;

    /**
     * 索引关键词
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCrName() {
        return crName;
    }

    public void setCrName(String crName) {
        this.crName = crName;
    }
}
