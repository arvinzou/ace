package com.huacainfo.ace.taa.vo;


import com.huacainfo.ace.taa.model.RoadDangerReport;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;

import java.util.List;

public class RoadDangerReportVo extends RoadDangerReport {
    private static final long serialVersionUID = 1L;
    private String uname;

    private String departmentName;

    private String roadManName;

    private String roadSectionName;

    private String areaName;

    private String keyword;

    private String fileUrl;


    private List<String> noChangedImagesUrl;

    private List<String> ChangedImagesUrl;

    private List<RoadDangerReportPic> picList;
    private List<RoadDangerReportPic> changedList;
    /**
     * 内勤人员  1-是； 0-否
     */
    private String officeAdmin;

    public List<RoadDangerReportPic> getPicList() {
        return picList;
    }

    public void setPicList(List<RoadDangerReportPic> picList) {
        this.picList = picList;
    }

    public List<String> getNoChangedImagesUrl() {
        return noChangedImagesUrl;
    }

    public void setNoChangedImagesUrl(List<String> noChangedImagesUrl) {
        this.noChangedImagesUrl = noChangedImagesUrl;
    }

    public List<String> getChangedImagesUrl() {
        return ChangedImagesUrl;
    }

    public void setChangedImagesUrl(List<String> changedImagesUrl) {
        ChangedImagesUrl = changedImagesUrl;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public String getOfficeAdmin() {
        return officeAdmin;
    }

    public void setOfficeAdmin(String officeAdmin) {
        this.officeAdmin = officeAdmin;
    }

    public List<RoadDangerReportPic> getChangedList() {
        return changedList;
    }

    public void setChangedList(List<RoadDangerReportPic> changedList) {
        this.changedList = changedList;
    }
}
