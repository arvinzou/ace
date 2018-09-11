package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Behavior;


public class BehaviorVo extends Behavior {
    private static final long serialVersionUID = 1L;

    private String fileType;

    private String fileName;

    private String fileSize;

    private String fileUrl;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
