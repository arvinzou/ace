package com.huacainfo.ace.society.model;

/**
 * Created by chenxiaoke on 2018/1/12.
 */
public class Img implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String url;

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
}
