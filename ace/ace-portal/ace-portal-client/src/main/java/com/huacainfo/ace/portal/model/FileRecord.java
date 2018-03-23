package com.huacainfo.ace.portal.model;

import java.util.Date;

public class FileRecord implements java.io.Serializable{


    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String path;

    private String mp4;

    private String url;

    private String status;

    private Long bytes_in;

    private Long bytes_out;

    private String addr;

    private String flashver;

    private String clientid;

    private Date updateTime;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4 == null ? null : mp4.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getBytes_in() {
        return bytes_in;
    }

    public void setBytes_in(Long bytes_in) {
        this.bytes_in = bytes_in;
    }

    public Long getBytes_out() {
        return bytes_out;
    }

    public void setBytes_out(Long bytes_out) {
        this.bytes_out = bytes_out;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getFlashver() {
        return flashver;
    }

    public void setFlashver(String flashver) {
        this.flashver = flashver == null ? null : flashver.trim();
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}