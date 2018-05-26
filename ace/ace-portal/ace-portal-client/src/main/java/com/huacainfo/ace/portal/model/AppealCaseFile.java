package com.huacainfo.ace.portal.model;

import java.util.Date;

public class AppealCaseFile implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String appealCaseId;

    private String name;

    private String type;

    /**
     * img - 图片，file - 文件 ，video- 视频，audio - 音频
     */
    private String mediType;

    private String mediUrl;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppealCaseId() {
        return appealCaseId;
    }

    public void setAppealCaseId(String appealCaseId) {
        this.appealCaseId = appealCaseId == null ? null : appealCaseId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMediType() {
        return mediType;
    }

    public void setMediType(String mediType) {
        this.mediType = mediType == null ? null : mediType.trim();
    }

    public String getMediUrl() {
        return mediUrl;
    }

    public void setMediUrl(String mediUrl) {
        this.mediUrl = mediUrl == null ? null : mediUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}