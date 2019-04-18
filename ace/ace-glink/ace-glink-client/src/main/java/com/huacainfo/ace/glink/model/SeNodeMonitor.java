package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeNodeMonitor extends BaseModel {
    private String id;

    private Integer nodeID;

    private String reportTime;

    private Integer gateStatus;

    private String gateReportTime;

    private Integer routeStatus;

    private String routeSignal;

    private String routeReportTime;

    private Integer currentPreset;

    private String presetCaption;

    private String presetReportTime;

    private String temperature;

    private String humidity;

    private String wSDReportTime;

    private String doorStatus;

    private String meterID;

    private String meterValue;

    private String meterReportTime;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getNodeID() {
        return nodeID;
    }

    public void setNodeID(Integer nodeID) {
        this.nodeID = nodeID;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public Integer getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(Integer gateStatus) {
        this.gateStatus = gateStatus;
    }

    public String getGateReportTime() {
        return gateReportTime;
    }

    public void setGateReportTime(String gateReportTime) {
        this.gateReportTime = gateReportTime == null ? null : gateReportTime.trim();
    }

    public Integer getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(Integer routeStatus) {
        this.routeStatus = routeStatus;
    }

    public String getRouteSignal() {
        return routeSignal;
    }

    public void setRouteSignal(String routeSignal) {
        this.routeSignal = routeSignal == null ? null : routeSignal.trim();
    }

    public String getRouteReportTime() {
        return routeReportTime;
    }

    public void setRouteReportTime(String routeReportTime) {
        this.routeReportTime = routeReportTime == null ? null : routeReportTime.trim();
    }

    public Integer getCurrentPreset() {
        return currentPreset;
    }

    public void setCurrentPreset(Integer currentPreset) {
        this.currentPreset = currentPreset;
    }

    public String getPresetCaption() {
        return presetCaption;
    }

    public void setPresetCaption(String presetCaption) {
        this.presetCaption = presetCaption == null ? null : presetCaption.trim();
    }

    public String getPresetReportTime() {
        return presetReportTime;
    }

    public void setPresetReportTime(String presetReportTime) {
        this.presetReportTime = presetReportTime == null ? null : presetReportTime.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public String getwSDReportTime() {
        return wSDReportTime;
    }

    public void setwSDReportTime(String wSDReportTime) {
        this.wSDReportTime = wSDReportTime == null ? null : wSDReportTime.trim();
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus == null ? null : doorStatus.trim();
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID == null ? null : meterID.trim();
    }

    public String getMeterValue() {
        return meterValue;
    }

    public void setMeterValue(String meterValue) {
        this.meterValue = meterValue == null ? null : meterValue.trim();
    }

    public String getMeterReportTime() {
        return meterReportTime;
    }

    public void setMeterReportTime(String meterReportTime) {
        this.meterReportTime = meterReportTime == null ? null : meterReportTime.trim();
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
