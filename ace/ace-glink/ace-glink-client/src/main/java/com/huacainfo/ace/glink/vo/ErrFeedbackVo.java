package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.ErrFeedback;


public class ErrFeedbackVo extends ErrFeedback {
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 建筑名称
     */
    private String buildingName;
    /**
     * 节点代码
     */
    private String nodeCode;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 站点代码
     */
    private String stationCode;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 分区代码
     */
    private String subareaCode;
    /**
     * 分区名称
     */
    private String subareaName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getSubareaCode() {
        return subareaCode;
    }

    public void setSubareaCode(String subareaCode) {
        this.subareaCode = subareaCode;
    }

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }
}
