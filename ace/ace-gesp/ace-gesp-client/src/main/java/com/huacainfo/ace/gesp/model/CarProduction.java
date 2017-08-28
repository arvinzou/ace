package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class CarProduction implements java.io.Serializable{
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	private String id;

    private String identiCode;

    private String carCategory;

    private String carType;

    private String carUseType;

    private String vin;

    private String subsidyId;

    private String overallSize;

    private String totalMass;

    private String loadQuality;

    private String factoryTime;

    private String bodySize;

    private String batteryGroupNo;

    private String batteryUnitTypeNo;

    private String batteryUnitMakerName;

    private String batteryBoxTypeNo;

    private String batteryGroupTotalCapacity;

    private String batteryGroupMakerName;

    private String batteryKeepYears;

    private String motorNo;

    private String motorTypeNo;

    private String motorRatedPower;

    private String motorMakerName;

    private String motorKeepYears;

    private String bodyColor;

    private String makerId;

    private String makerName;

    private String carStatus;

    private String simNo;

    private Integer deleteMark;

    private Date createDate;

    private String createUserId;

    private String createUserName;

    private Date modifyDate;

    private String modifyUserId;

    private String modifyUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIdentiCode() {
        return identiCode;
    }

    public void setIdentiCode(String identiCode) {
        this.identiCode = identiCode == null ? null : identiCode.trim();
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory == null ? null : carCategory.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarUseType() {
        return carUseType;
    }

    public void setCarUseType(String carUseType) {
        this.carUseType = carUseType == null ? null : carUseType.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getSubsidyId() {
        return subsidyId;
    }

    public void setSubsidyId(String subsidyId) {
        this.subsidyId = subsidyId == null ? null : subsidyId.trim();
    }

    public String getOverallSize() {
        return overallSize;
    }

    public void setOverallSize(String overallSize) {
        this.overallSize = overallSize == null ? null : overallSize.trim();
    }

    public String getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(String totalMass) {
        this.totalMass = totalMass == null ? null : totalMass.trim();
    }

    public String getLoadQuality() {
        return loadQuality;
    }

    public void setLoadQuality(String loadQuality) {
        this.loadQuality = loadQuality == null ? null : loadQuality.trim();
    }

    public String getFactoryTime() {
        return factoryTime;
    }

    public void setFactoryTime(String factoryTime) {
        this.factoryTime = factoryTime == null ? null : factoryTime.trim();
    }

    public String getBodySize() {
        return bodySize;
    }

    public void setBodySize(String bodySize) {
        this.bodySize = bodySize == null ? null : bodySize.trim();
    }

    public String getBatteryGroupNo() {
        return batteryGroupNo;
    }

    public void setBatteryGroupNo(String batteryGroupNo) {
        this.batteryGroupNo = batteryGroupNo == null ? null : batteryGroupNo.trim();
    }

    public String getBatteryUnitTypeNo() {
        return batteryUnitTypeNo;
    }

    public void setBatteryUnitTypeNo(String batteryUnitTypeNo) {
        this.batteryUnitTypeNo = batteryUnitTypeNo == null ? null : batteryUnitTypeNo.trim();
    }

    public String getBatteryUnitMakerName() {
        return batteryUnitMakerName;
    }

    public void setBatteryUnitMakerName(String batteryUnitMakerName) {
        this.batteryUnitMakerName = batteryUnitMakerName == null ? null : batteryUnitMakerName.trim();
    }

    public String getBatteryBoxTypeNo() {
        return batteryBoxTypeNo;
    }

    public void setBatteryBoxTypeNo(String batteryBoxTypeNo) {
        this.batteryBoxTypeNo = batteryBoxTypeNo == null ? null : batteryBoxTypeNo.trim();
    }

    public String getBatteryGroupTotalCapacity() {
        return batteryGroupTotalCapacity;
    }

    public void setBatteryGroupTotalCapacity(String batteryGroupTotalCapacity) {
        this.batteryGroupTotalCapacity = batteryGroupTotalCapacity == null ? null : batteryGroupTotalCapacity.trim();
    }

    public String getBatteryGroupMakerName() {
        return batteryGroupMakerName;
    }

    public void setBatteryGroupMakerName(String batteryGroupMakerName) {
        this.batteryGroupMakerName = batteryGroupMakerName == null ? null : batteryGroupMakerName.trim();
    }

    public String getBatteryKeepYears() {
        return batteryKeepYears;
    }

    public void setBatteryKeepYears(String batteryKeepYears) {
        this.batteryKeepYears = batteryKeepYears == null ? null : batteryKeepYears.trim();
    }

    public String getMotorNo() {
        return motorNo;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo == null ? null : motorNo.trim();
    }

    public String getMotorTypeNo() {
        return motorTypeNo;
    }

    public void setMotorTypeNo(String motorTypeNo) {
        this.motorTypeNo = motorTypeNo == null ? null : motorTypeNo.trim();
    }

    public String getMotorRatedPower() {
        return motorRatedPower;
    }

    public void setMotorRatedPower(String motorRatedPower) {
        this.motorRatedPower = motorRatedPower == null ? null : motorRatedPower.trim();
    }

    public String getMotorMakerName() {
        return motorMakerName;
    }

    public void setMotorMakerName(String motorMakerName) {
        this.motorMakerName = motorMakerName == null ? null : motorMakerName.trim();
    }

    public String getMotorKeepYears() {
        return motorKeepYears;
    }

    public void setMotorKeepYears(String motorKeepYears) {
        this.motorKeepYears = motorKeepYears == null ? null : motorKeepYears.trim();
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor == null ? null : bodyColor.trim();
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId == null ? null : makerId.trim();
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName == null ? null : makerName.trim();
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus == null ? null : carStatus.trim();
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo == null ? null : simNo.trim();
    }

    public Integer getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }
}