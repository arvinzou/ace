package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LeBrokenLamp;


public class LeBrokenLampVo extends LeBrokenLamp {
    /**
     * 控制器名称
     */
    private String ctrlName;
    /**
     * 建筑物名称-故障位置
     */
    private String buildingName;

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
