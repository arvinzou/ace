package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LoopCtrlNode;


public class LoopCtrlNodeVo extends LoopCtrlNode {
    private String nodeName;
    private String buName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }
}
