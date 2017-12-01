package com.huacainfo.ace.uf.model;

import java.util.Date;

public class File {
    private static final long serialVersionUID = 1L;
    /**
     * 文件编号
     */
    private String id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件url
     */
    private String file;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 创建人编号
     */
    private String createUserId;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 入库时间
     */
    private Date createDate;
    /**
     * 最后更新人编号
     */
    private String lastModifyUserId;
    /**
     * 最后更新人姓名
     */
    private String lastModifyUserName;
    /**
     * 最后更新时间
     */
    private Date lastModifyDate;

    /**
     *文件大小
     */
    private String fileSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public File() {
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", file='" + file + '\'' +
                ", suffix='" + suffix + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", createDate=" + createDate +
                ", lastModifyUserId='" + lastModifyUserId + '\'' +
                ", lastModifyUserName='" + lastModifyUserName + '\'' +
                ", lastModifyDate=" + lastModifyDate +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
