package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopDevice;


public class TopDeviceVo extends TopDevice {
    private static final long serialVersionUID = 1L;

    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
