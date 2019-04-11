package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopStation;

import java.util.List;


public class TopStationVo extends TopStation {
    private static final long serialVersionUID = 1L;

    private String subareaName;


    /**
     * 归属节点列表
     */
    private List<TopNodeVo> nodeList;

    private int deviceNum;
    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }

    public List<TopNodeVo> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<TopNodeVo> nodeList) {
        this.nodeList = nodeList;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }
}
