package com.huacainfo.ace.live.model;

public class LiveRptWithBLOBs extends LiveRpt {
    private String content;

    private String mediaContent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent == null ? null : mediaContent.trim();
    }
}