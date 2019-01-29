package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.jxb.model.WithdrawRecord;

import java.util.Map;


public class WithdrawRecordVo extends WithdrawRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 咨询师名称
     */
    private String teacherName;

    /**
     * 微信接口提现日志
     */
    private String apiJson;

    /**
     * 接口处理状态  SUCCESS:转账成功     FAIL:转账失败
     */
    private String apiRst;

    /**
     * 接口异常信息
     */
    private String apiRemark;

    private Map<String, Object> apiRstMap;

    public String getApiJson() {
        return apiJson;
    }

    public void setApiJson(String apiJson) {
        this.apiJson = apiJson;
    }

    public String getApiRemark() {
        if (null == apiRstMap) {
            apiRstMap = JsonUtil.toMap(getApiJson());
        }
        if (apiRstMap != null && null != apiRstMap.get("return_msg")) {
            return String.valueOf(apiRstMap.get("return_msg"));
        }

        return apiRemark;
    }

    public void setApiRemark(String apiRemark) {
        this.apiRemark = apiRemark;
    }

    public String getApiRst() {
        if (null == apiRstMap) {
            apiRstMap = JsonUtil.toMap(getApiJson());
        }
        if (apiRstMap != null && null != apiRstMap.get("result_code")) {
            return String.valueOf(apiRstMap.get("result_code"));
        }
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
