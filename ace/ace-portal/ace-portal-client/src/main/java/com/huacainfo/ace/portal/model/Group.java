package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Group implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String groupId;

    private String groupName;

    private String sqlText;

    private String remark;
    private String syid;

    private Date createTime;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText == null ? null : sqlText.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSyid() {
        return syid;
    }

    public void setSyid(String syid) {
        this.syid = syid;
    }
}