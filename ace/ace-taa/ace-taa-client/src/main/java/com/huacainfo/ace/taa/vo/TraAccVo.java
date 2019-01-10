package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.TraAcc;


public class TraAccVo extends TraAcc {

    private static final long serialVersionUID = 1L;

    private String roadManName;

    private String roadSectionName;

    private String areaName;

    private String category;

    public String getRoadManName() {
        return roadManName;
    }

    public void setRoadManName(String roadManName) {
        this.roadManName = roadManName;
    }

    public String getRoadSectionName() {
        return roadSectionName;
    }

    public void setRoadSectionName(String roadSectionName) {
        this.roadSectionName = roadSectionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
