package com.huacainfo.ace.rvc.vo;

/**
 * Created by kevin on 2017/12/22.
 * 会议资料entity
 */
public class ConferenceResVO {

    private String id;

    // 文件名称
    private String fileName;

    // 创建时间
    private String date;

    // 创建人
    private String author;

    // 文件大小
    private double size;

    // 文件类型
    // 0 doc docx, 1 xls xlsx, 2 pdf
    private int fileType;

    // 文件地址
    private String fileURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
