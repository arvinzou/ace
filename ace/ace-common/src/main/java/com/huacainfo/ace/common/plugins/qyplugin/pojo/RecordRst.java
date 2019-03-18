package com.huacainfo.ace.common.plugins.qyplugin.pojo;


import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ApiRst;

/**
 * @ClassName RecordRst
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/18 10:55
 */
public class RecordRst extends ApiRst {

    private RecordData data;

    public RecordData getData() {
        return data;
    }

    public void setData(RecordData data) {
        this.data = data;
    }
}
