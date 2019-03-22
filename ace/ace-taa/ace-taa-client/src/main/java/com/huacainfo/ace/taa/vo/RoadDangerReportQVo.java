package com.huacainfo.ace.taa.vo;


import com.huacainfo.ace.taa.model.RoadDangerReport;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;

import java.util.List;

public class RoadDangerReportQVo extends RoadDangerReport {
    private static final long serialVersionUID = 1L;
    private String uname;  //用户名
    private String departmentName; //单位名称
    private String roadManName; //路长名

    private String roadSectionName; //路段名

    private String areaName;//地区名
    private String keyword;

    private String fileUrl;
    //获取未整改图片路径
    private List<String> noChangedImagesUrl;
    //获取未整改图片路径
    private List<String> ChangedImagesUrl;
    //获取未整改图片list
    private List<RoadDangerReportPic> picList;
    //获取整改图片list
    private List<RoadDangerReportPic> changedList;
    /**
     * 内勤人员  1-是； 0-否
     */
    private String officeAdmin;

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

    public List<RoadDangerReportPic> getPicList() {
        return picList;
    }

    public void setPicList(List<RoadDangerReportPic> picList) {
        this.picList = picList;
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
