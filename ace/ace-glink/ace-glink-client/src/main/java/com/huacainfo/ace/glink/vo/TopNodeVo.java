package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopBuilding;
import com.huacainfo.ace.glink.model.TopNode;
import com.huacainfo.ace.glink.model.TopStation;


public class TopNodeVo extends TopNode {
    private static final long serialVersionUID = 1L;

    private TopBuilding topBuilding;

    private TopStation topStation;

    private int deviceCount;

    public TopBuilding getTopBuilding() {
        return topBuilding;
    }

    public void setTopBuilding(TopBuilding topBuilding) {
        this.topBuilding = topBuilding;
    }

    public TopStation getTopStation() {
        return topStation;
    }

    public void setTopStation(TopStation topStation) {
        this.topStation = topStation;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
