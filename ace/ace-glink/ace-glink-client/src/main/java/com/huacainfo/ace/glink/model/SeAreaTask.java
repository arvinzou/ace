package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeAreaTask extends BaseModel {
    private String id;

    private String areaNodeID;

    private int taskNo;

    private String taskName;

    private String remark;

    private String status;

    private Date createDate;

    /**
     * 执行状态 "ok"-已执行；"error"-未执行
     */
    private String exeState;
    /**
     * 执行结果 ，接口返回值
     */
    private String exeRst;

    public String getExeState() {
        return exeState;
    }

    public void setExeState(String exeState) {
        this.exeState = exeState;
    }

    public String getExeRst() {
        return exeRst;
    }

    public void setExeRst(String exeRst) {
        this.exeRst = exeRst;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaNodeID() {
        return areaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        this.areaNodeID = areaNodeID == null ? null : areaNodeID.trim();
    }

    public int getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
