package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.EnrollRoster;


public class EnrollRosterVo extends EnrollRoster {
    private static final long serialVersionUID = 1L;
    /**
     * 班次名称
     */
    private String clsViewName;

    public String getClsViewName() {
        return clsViewName;
    }

    public void setClsViewName(String clsViewName) {
        this.clsViewName = clsViewName;
    }
}
