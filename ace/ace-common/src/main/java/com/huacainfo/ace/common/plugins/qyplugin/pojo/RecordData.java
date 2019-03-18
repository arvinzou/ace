package com.huacainfo.ace.common.plugins.qyplugin.pojo;


import com.huacainfo.ace.common.plugins.qyplugin.pojo.base.ListData;

import java.util.List;

/**
 * @ClassName RecordData
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/18 10:49
 */
public class RecordData extends ListData {

    /**
     * 数据集合
     */
    private List<RecordLog> attendata;

    public List<RecordLog> getAttendata() {
        return attendata;
    }

    public void setAttendata(List<RecordLog> attendata) {
        this.attendata = attendata;
    }
}
