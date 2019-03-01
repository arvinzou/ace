package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RoadGps;


public class RoadGpsVo extends RoadGps {
    private static final long serialVersionUID = 1L;
    /**
     * 路长姓名
     */
    private String manName;
    /**
     * 道路名称
     */
    private String roadName;
    /**
     * 路段名称
     */
    private String sectionName;
    private String startName;
    private String endName;
    /**
     * 两点距离
     */
    private double distance;

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
