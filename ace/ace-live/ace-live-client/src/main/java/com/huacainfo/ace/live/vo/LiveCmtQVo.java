package com.huacainfo.ace.live.vo;

import com.huacainfo.ace.live.model.LiveCmt;


public class LiveCmtQVo extends LiveCmt {
    private static final long serialVersionUID = 1L;
    private  String deptId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
