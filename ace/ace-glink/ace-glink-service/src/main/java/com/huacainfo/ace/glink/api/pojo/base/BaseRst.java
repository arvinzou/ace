package com.huacainfo.ace.glink.api.pojo.base;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName BaseRst
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/15 10:31
 */
public class BaseRst extends BaseModel {
    /**
     * 返回码:
     * 200: 成功
     * 400: 失败
     */
    private int code;
    /**
     * 返回描述
     */
    private String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
