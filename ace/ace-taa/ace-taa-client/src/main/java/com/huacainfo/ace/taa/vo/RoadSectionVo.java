package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RoadSection;


public class RoadSectionVo extends RoadSection {
private static final long serialVersionUID = 1L;

    private String roadManName;
    private String areaName;

    /**
     * 查询类别  1-已采集 ; 0 - 未采集
     */
    private String category;
    /**
     * GPS采集条数
     */
    private int gpsNum;

    public int getGpsNum() {
        return gpsNum;
    }

    public void setGpsNum(int gpsNum) {
        this.gpsNum = gpsNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
