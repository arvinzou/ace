package com.huacainfo.ace.society.model;

import java.util.Date;
import java.util.List;

/**
 * Created by chenxiaoke on 2018/1/12.
 */
public class Rpt implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String content;
    private String createDate;
    private List<Img> imageList;
    private String reportId;
    private String type;
    private String video;
    private Long likeNum;
    private String status;

    private String auditMessage;

    private Reporter rpt;
    private List<Comments> comments;





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


    public List<Img> getImageList() {
        return imageList;
    }

    public void setImageList(List<Img> imageList) {
        this.imageList = imageList;
    }


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }
}
