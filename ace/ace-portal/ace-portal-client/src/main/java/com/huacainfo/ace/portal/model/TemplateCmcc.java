package com.huacainfo.ace.portal.model;

import java.util.Date;

public class TemplateCmcc implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String templateCmccId;

    private String name;

    private String content;

    private Date createTime;

    public String getTemplateCmccId() {
        return templateCmccId;
    }

    public void setTemplateCmccId(String templateCmccId) {
        this.templateCmccId = templateCmccId == null ? null : templateCmccId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}