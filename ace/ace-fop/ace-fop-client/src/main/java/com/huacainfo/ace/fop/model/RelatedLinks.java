package com.huacainfo.ace.fop.model;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.Date;

/**
 * @author HuaCai003
 */
public class RelatedLinks implements Serializable {

    private static final long serialVersionUID = -3659486117311180125L;
    private String id;

    private String parentId;

    private String name;

    private String url;

    private String alternateFields1;

    private String alternateFields2;

    private String alternateFields3;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAlternateFields1() {
        return alternateFields1;
    }

    public void setAlternateFields1(String alternateFields1) {
        this.alternateFields1 = alternateFields1 == null ? null : alternateFields1.trim();
    }

    public String getAlternateFields2() {
        return alternateFields2;
    }

    public void setAlternateFields2(String alternateFields2) {
        this.alternateFields2 = alternateFields2 == null ? null : alternateFields2.trim();
    }

    public String getAlternateFields3() {
        return alternateFields3;
    }

    public void setAlternateFields3(String alternateFields3) {
        this.alternateFields3 = alternateFields3 == null ? null : alternateFields3.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}