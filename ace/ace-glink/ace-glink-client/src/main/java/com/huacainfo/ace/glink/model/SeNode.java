package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeNode extends BaseModel {
    private String id;

    private Integer nodeCount;

    private String nodeID;

    private String local;

    private String iPAddress;

    private String routeIPAddress;

    private String areaNodeID;

    private String pX;

    private String pY;

    private String meterID;

    private String meterType;

    private String engineer;

    private String tel;

    private Integer deviceCount;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local == null ? null : local.trim();
    }

    public String getiPAddress() {
        return iPAddress;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress == null ? null : iPAddress.trim();
    }

    public String getRouteIPAddress() {
        return routeIPAddress;
    }

    public void setRouteIPAddress(String routeIPAddress) {
        this.routeIPAddress = routeIPAddress == null ? null : routeIPAddress.trim();
    }

    public String getAreaNodeID() {
        return areaNodeID;
    }

    public void setAreaNodeID(String areaNodeID) {
        this.areaNodeID = areaNodeID == null ? null : areaNodeID.trim();
    }

    public String getpX() {
        return pX;
    }

    public void setpX(String pX) {
        this.pX = pX == null ? null : pX.trim();
    }

    public String getpY() {
        return pY;
    }

    public void setpY(String pY) {
        this.pY = pY == null ? null : pY.trim();
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID == null ? null : meterID.trim();
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType == null ? null : meterType.trim();
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer == null ? null : engineer.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
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
