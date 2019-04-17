package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LoopCtrlNode;


public class LoopCtrlNodeQVo extends LoopCtrlNode {

    /**
     * 搜索关键字
     */
    private String keyword;
    private String stationCode;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
}
