package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeGatewayState;


public class SeGatewayStateQVo extends SeGatewayState {

    /**
     * 搜索关键字
     */
    private String keyword;

    private String areaNodeID;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAreaNodeID() {
        return areaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        this.areaNodeID = areaNodeID;
    }
}
