package com.huacainfo.ace.rvc.kedapi.common.base;

import com.huacainfo.ace.common.tools.JsonUtil;

/**
 * @description: 公共参数
 * @author: ArvinZou
 * @create: 2017-11-17 9:03
 */
public class BaseModel {
    /**
     * 成功标志 1-成功，0-失败
     */
    private int success;
    /**
     * 错误代码
     * com.huacainfo.ace.rvc.kdapi.constant.ErrorCode
     */
    private int error_code;

    /**
     * 错误描述
     */
    private String description;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
