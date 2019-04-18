package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.model.SeNodeDevice;

import java.util.List;


public class SeNodeVo extends SeNode {

    private String areaNodeName;

    private List<SeNodeDevice> deviceList;

    public List<SeNodeDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<SeNodeDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public String getAreaNodeName() {
        return areaNodeName;
    }

    public void setAreaNodeName(String areaNodeName) {
        this.areaNodeName = areaNodeName;
    }
}
