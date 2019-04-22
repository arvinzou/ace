package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeNodeMonitor;
import com.huacainfo.ace.glink.model.SeNodeMonitorDevice;

import java.util.List;

/**
 * @ClassName SeNodeMonitorVo
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/18 20:39
 */
public class SeNodeMonitorQVo extends SeNodeMonitor {

    /**
     * 搜索关键字
     */
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
