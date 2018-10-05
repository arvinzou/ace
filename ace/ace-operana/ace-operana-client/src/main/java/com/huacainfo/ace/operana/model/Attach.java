package com.huacainfo.ace.operana.model;

/**
 * Created by chenxiaoke on 2018/10/5.
 */
public class Attach implements java.io.Serializable{

    private String name;

    private String url;

    private String attachId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }
}
