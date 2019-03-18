package com.huacainfo.ace.common.plugins.qyplugin.pojo.base;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName ApiRst
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/14 11:44
 */
public class ApiRst extends BaseModel {
    /**
     * 1为成功，其它失败
     */
    private int status;
    /**
     * 如果失败有返回
     */
    private String error;

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
