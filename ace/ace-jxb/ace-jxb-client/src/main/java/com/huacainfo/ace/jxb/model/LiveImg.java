package com.huacainfo.ace.jxb.model;

import java.util.Date;

public class LiveImg implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String rptId;

    private String url;

    private Integer w;

    private Integer h;

    private String watermarkConfig;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRptId() {
        return rptId;
    }

    public void setRptId(String rptId) {
        this.rptId = rptId == null ? null : rptId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public String getWatermarkConfig() {
        return watermarkConfig;
    }

    public void setWatermarkConfig(String watermarkConfig) {
        this.watermarkConfig = watermarkConfig == null ? null : watermarkConfig.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}