package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class CarInfo implements java.io.Serializable{
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	private String id;

    private String plateNumber;

    private String plateColor;

    private Date drivingLicCreatetime;

    private String drivingLicNo;

    private String carType;

    private String vin;

    private String batteryGroupNo;

    private String motorNo;

    private String transportNo;

    private String passNo;

    private String bussType;

    private String bussStatus;

    private String isGAT;

    private String gATNo;

    private String ownerId;

    private String ownerName;

    private String insuranceCompany;

    private String coverage;

    private Date insuranceDate;

    private Date lastComperformCheckDate;

    private Date lastClassIIDate;

    private String dirverId;
    private String dirverName;

    private String isInstallOBD;

    private String odbDeviceId;

    private String isInstallGPS;

    private String fuelType;

    private String makerName;

    private String ownerIdCardNo;

    private String ownerPhone;

    private Date initRegDate;

    private Date validEndDate;

    private Date scrapEndDate;

    private String transportMode;

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor == null ? null : plateColor.trim();
    }

    public Date getDrivingLicCreatetime() {
        return drivingLicCreatetime;
    }

    public void setDrivingLicCreatetime(Date drivingLicCreatetime) {
        this.drivingLicCreatetime = drivingLicCreatetime;
    }

    public String getDrivingLicNo() {
        return drivingLicNo;
    }

    public void setDrivingLicNo(String drivingLicNo) {
        this.drivingLicNo = drivingLicNo == null ? null : drivingLicNo.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getBatteryGroupNo() {
        return batteryGroupNo;
    }

    public void setBatteryGroupNo(String batteryGroupNo) {
        this.batteryGroupNo = batteryGroupNo == null ? null : batteryGroupNo.trim();
    }

    public String getMotorNo() {
        return motorNo;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo == null ? null : motorNo.trim();
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo == null ? null : transportNo.trim();
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo == null ? null : passNo.trim();
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    public String getBussStatus() {
        return bussStatus;
    }

    public void setBussStatus(String bussStatus) {
        this.bussStatus = bussStatus == null ? null : bussStatus.trim();
    }

    public String getIsGAT() {
        return isGAT;
    }

    public void setIsGAT(String isGAT) {
        this.isGAT = isGAT == null ? null : isGAT.trim();
    }

    public String getgATNo() {
        return gATNo;
    }

    public void setgATNo(String gATNo) {
        this.gATNo = gATNo == null ? null : gATNo.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany == null ? null : insuranceCompany.trim();
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage == null ? null : coverage.trim();
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getLastComperformCheckDate() {
        return lastComperformCheckDate;
    }

    public void setLastComperformCheckDate(Date lastComperformCheckDate) {
        this.lastComperformCheckDate = lastComperformCheckDate;
    }

    public Date getLastClassIIDate() {
        return lastClassIIDate;
    }

    public void setLastClassIIDate(Date lastClassIIDate) {
        this.lastClassIIDate = lastClassIIDate;
    }

    public String getDirverId() {
        return dirverId;
    }

    public void setDirverId(String dirverId) {
        this.dirverId = dirverId == null ? null : dirverId.trim();
    }

    public String getIsInstallOBD() {
        return isInstallOBD;
    }

    public void setIsInstallOBD(String isInstallOBD) {
        this.isInstallOBD = isInstallOBD == null ? null : isInstallOBD.trim();
    }

    public String getOdbDeviceId() {
        return odbDeviceId;
    }

    public void setOdbDeviceId(String odbDeviceId) {
        this.odbDeviceId = odbDeviceId == null ? null : odbDeviceId.trim();
    }

    public String getIsInstallGPS() {
        return isInstallGPS;
    }

    public void setIsInstallGPS(String isInstallGPS) {
        this.isInstallGPS = isInstallGPS == null ? null : isInstallGPS.trim();
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType == null ? null : fuelType.trim();
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName == null ? null : makerName.trim();
    }

    public String getOwnerIdCardNo() {
        return ownerIdCardNo;
    }

    public void setOwnerIdCardNo(String ownerIdCardNo) {
        this.ownerIdCardNo = ownerIdCardNo == null ? null : ownerIdCardNo.trim();
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone == null ? null : ownerPhone.trim();
    }

    public Date getInitRegDate() {
        return initRegDate;
    }

    public void setInitRegDate(Date initRegDate) {
        this.initRegDate = initRegDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public Date getScrapEndDate() {
        return scrapEndDate;
    }

    public void setScrapEndDate(Date scrapEndDate) {
        this.scrapEndDate = scrapEndDate;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode == null ? null : transportMode.trim();
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

	public String getDirverName() {
		return dirverName;
	}

	public void setDirverName(String dirverName) {
		this.dirverName = dirverName;
	}
    
}