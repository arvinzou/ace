package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.QyCrm;


public class QyCrmQVo extends QyCrm {
    private static final long serialVersionUID = 1L;
    /***
     * 关键字搜索
     */
    private String keyword;

    private String classId;

    private String areaCode;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
