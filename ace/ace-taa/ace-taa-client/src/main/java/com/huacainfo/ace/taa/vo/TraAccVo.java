package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.model.TraAccCause;
import com.huacainfo.ace.taa.model.TraAccMtype;

import java.util.List;


public class TraAccVo extends TraAcc {

    private static final long serialVersionUID = 1L;

    private String roadManName;

    private String roadSectionName;

    private String areaName;

    private String category;
    /**
     * 道路名称
     */
    private String roadName;

    /**
     * 事故原因列表
     */
    private List<TraAccCause> causeList;

    /**
     * 事故车型列表
     */
    private List<TraAccMtype> mtypeList;

    public List<TraAccCause> getCauseList() {
        return causeList;
    }

    public void setCauseList(List<TraAccCause> causeList) {
        this.causeList = causeList;
    }

    public List<TraAccMtype> getMtypeList() {
        return mtypeList;
    }

    public void setMtypeList(List<TraAccMtype> mtypeList) {
        this.mtypeList = mtypeList;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

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
