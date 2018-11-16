package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.WithdrawRecord;


public class WithdrawRecordVo extends WithdrawRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 咨询师名称
     */
    private String teacherName;

    /**
     * 接口处理状态  SUCCESS:转账成功     FAILED:转账失败
     */
    private String apiRst;

    /**
     * 接口异常信息
     */
    private String apiRemark;

    public String getApiRemark() {
        return apiRemark;
    }

    public void setApiRemark(String apiRemark) {
        this.apiRemark = apiRemark;
    }

    public String getApiRst() {
        return apiRst;
    }

    public void setApiRst(String apiRst) {
        this.apiRst = apiRst;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
