package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LeBrokenLamp;

import java.math.BigDecimal;


public class LeBrokenLampVo extends LeBrokenLamp {
    /**
     * 控制器名称
     */
    private String ctrlName;
    /**
     * 建筑物名称-故障位置
     */
    private String buildingName;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getCtrlName() {
        return ctrlName;
    }

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
