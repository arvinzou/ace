package com.huacainfo.ace.live.model;

import java.util.Date;
import java.util.List;

/**
 * Created by chenxiaoke on 2018/1/12.
 */
public class Rpt implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Date approvedTime;
    private String content;
    private String createDate;
    private int duration;
    private Date gmtCreate;
    private List<Img> imageList;
    private String pictures;
    private String reportId;
    private String reporter;
    private String thumbnail;
    private String type;
    private String userType;
    private String watermarkConfig;
    private String video;
    private String audio;
    Reporter rpt;
    private List<Comments> comments;

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public List<Img> getImageList() {
        return imageList;
    }

    public void setImageList(List<Img> imageList) {
        this.imageList = imageList;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getWatermarkConfig() {
        return watermarkConfig;
    }

    public void setWatermarkConfig(String watermarkConfig) {
        this.watermarkConfig = watermarkConfig;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }


    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Reporter getRpt() {
        return rpt;
    }

    public void setRpt(Reporter rpt) {
        this.rpt = rpt;
    }
}
