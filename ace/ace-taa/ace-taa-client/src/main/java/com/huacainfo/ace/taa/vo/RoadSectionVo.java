package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RoadSection;


public class RoadSectionVo extends RoadSection {
private static final long serialVersionUID = 1L;

    private String roadManName;
    private String areaName;


    public String getRoadManName() {
        return roadManName;
    }

    public void setRoadManName(String roadManName) {
        this.roadManName = roadManName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
