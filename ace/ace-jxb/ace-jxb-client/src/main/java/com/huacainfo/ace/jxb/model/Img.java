package com.huacainfo.ace.jxb.model;

/**
 * Created by chenxiaoke on 2018/1/12.
 */
public class Img implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String url;
    private String watermarkConfig;
    private int h;
    private int w;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWatermarkConfig() {
        return watermarkConfig;
    }

    public void setWatermarkConfig(String watermarkConfig) {
        this.watermarkConfig = watermarkConfig;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
