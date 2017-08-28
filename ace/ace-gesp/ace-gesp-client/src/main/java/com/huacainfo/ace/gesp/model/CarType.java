package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class CarType implements java.io.Serializable{
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	private String id;

    private String identiCode;

    private String carCode;

    private String carName;

    private String carType;

    private String imageFileIds;

    private String freeInspection;

    private String fuel;

    private String exempt;

    private String green;

    private String noticeBatches;

    private String directoryNum;

    private String chineseBrand;

    private String carMakerName;

    private String engineModel;

    private String engineCapacity;

    private String engineDisplacement;

    private String engineMakerName;

    private String engineBrand;

    private String fuelType;

    private String overallSize;

    private String bodySize;

    private String totalMass;

    private String loadQuality;

    private String setupQuality;

    private String ratedQuality;

    private String trailerQuality;

    private String semitrailer;

    private String ratedCapacity;

    private String frontCapacity;

    private String cab;

    private String releaseDate;

    private String frontOverhang;

    private String rearOverhang;

    private String approachAngle;

    private String departureAngle;

    private String wheelbase;

    private String axleLoad;

    private String axleCount;

    private String maximumSpeed;

    private String fuelConsumption;

    private String leafSpringCount;

    private String tireCount;

    private String tireSpec;

    private String frontGauge;

    private String brakeFront;

    private String brakeAfter;

    private String operationFront;

    private String operationAfter;

    private String steeringType;

    private String startType;

    private String drivingType;

    private String basicStandard;

    private String chassisBasicStandard;

    private String rearTrack;

    private String remark;

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

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode == null ? null : carCode.trim();
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getImageFileIds() {
        return imageFileIds;
    }

    public void setImageFileIds(String imageFileIds) {
        this.imageFileIds = imageFileIds == null ? null : imageFileIds.trim();
    }

    public String getFreeInspection() {
        return freeInspection;
    }

    public void setFreeInspection(String freeInspection) {
        this.freeInspection = freeInspection == null ? null : freeInspection.trim();
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel == null ? null : fuel.trim();
    }

    public String getExempt() {
        return exempt;
    }

    public void setExempt(String exempt) {
        this.exempt = exempt == null ? null : exempt.trim();
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green == null ? null : green.trim();
    }

    public String getNoticeBatches() {
        return noticeBatches;
    }

    public void setNoticeBatches(String noticeBatches) {
        this.noticeBatches = noticeBatches == null ? null : noticeBatches.trim();
    }

    public String getDirectoryNum() {
        return directoryNum;
    }

    public void setDirectoryNum(String directoryNum) {
        this.directoryNum = directoryNum == null ? null : directoryNum.trim();
    }

    public String getChineseBrand() {
        return chineseBrand;
    }

    public void setChineseBrand(String chineseBrand) {
        this.chineseBrand = chineseBrand == null ? null : chineseBrand.trim();
    }

    public String getCarMakerName() {
        return carMakerName;
    }

    public void setCarMakerName(String carMakerName) {
        this.carMakerName = carMakerName == null ? null : carMakerName.trim();
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel == null ? null : engineModel.trim();
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity == null ? null : engineCapacity.trim();
    }

    public String getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(String engineDisplacement) {
        this.engineDisplacement = engineDisplacement == null ? null : engineDisplacement.trim();
    }

    public String getEngineMakerName() {
        return engineMakerName;
    }

    public void setEngineMakerName(String engineMakerName) {
        this.engineMakerName = engineMakerName == null ? null : engineMakerName.trim();
    }

    public String getEngineBrand() {
        return engineBrand;
    }

    public void setEngineBrand(String engineBrand) {
        this.engineBrand = engineBrand == null ? null : engineBrand.trim();
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType == null ? null : fuelType.trim();
    }

    public String getOverallSize() {
        return overallSize;
    }

    public void setOverallSize(String overallSize) {
        this.overallSize = overallSize == null ? null : overallSize.trim();
    }

    public String getBodySize() {
        return bodySize;
    }

    public void setBodySize(String bodySize) {
        this.bodySize = bodySize == null ? null : bodySize.trim();
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

    public String getSetupQuality() {
        return setupQuality;
    }

    public void setSetupQuality(String setupQuality) {
        this.setupQuality = setupQuality == null ? null : setupQuality.trim();
    }

    public String getRatedQuality() {
        return ratedQuality;
    }

    public void setRatedQuality(String ratedQuality) {
        this.ratedQuality = ratedQuality == null ? null : ratedQuality.trim();
    }

    public String getTrailerQuality() {
        return trailerQuality;
    }

    public void setTrailerQuality(String trailerQuality) {
        this.trailerQuality = trailerQuality == null ? null : trailerQuality.trim();
    }

    public String getSemitrailer() {
        return semitrailer;
    }

    public void setSemitrailer(String semitrailer) {
        this.semitrailer = semitrailer == null ? null : semitrailer.trim();
    }

    public String getRatedCapacity() {
        return ratedCapacity;
    }

    public void setRatedCapacity(String ratedCapacity) {
        this.ratedCapacity = ratedCapacity == null ? null : ratedCapacity.trim();
    }

    public String getFrontCapacity() {
        return frontCapacity;
    }

    public void setFrontCapacity(String frontCapacity) {
        this.frontCapacity = frontCapacity == null ? null : frontCapacity.trim();
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab == null ? null : cab.trim();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate == null ? null : releaseDate.trim();
    }

    public String getFrontOverhang() {
        return frontOverhang;
    }

    public void setFrontOverhang(String frontOverhang) {
        this.frontOverhang = frontOverhang == null ? null : frontOverhang.trim();
    }

    public String getRearOverhang() {
        return rearOverhang;
    }

    public void setRearOverhang(String rearOverhang) {
        this.rearOverhang = rearOverhang == null ? null : rearOverhang.trim();
    }

    public String getApproachAngle() {
        return approachAngle;
    }

    public void setApproachAngle(String approachAngle) {
        this.approachAngle = approachAngle == null ? null : approachAngle.trim();
    }

    public String getDepartureAngle() {
        return departureAngle;
    }

    public void setDepartureAngle(String departureAngle) {
        this.departureAngle = departureAngle == null ? null : departureAngle.trim();
    }

    public String getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(String wheelbase) {
        this.wheelbase = wheelbase == null ? null : wheelbase.trim();
    }

    public String getAxleLoad() {
        return axleLoad;
    }

    public void setAxleLoad(String axleLoad) {
        this.axleLoad = axleLoad == null ? null : axleLoad.trim();
    }

    public String getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(String axleCount) {
        this.axleCount = axleCount == null ? null : axleCount.trim();
    }

    public String getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(String maximumSpeed) {
        this.maximumSpeed = maximumSpeed == null ? null : maximumSpeed.trim();
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption == null ? null : fuelConsumption.trim();
    }

    public String getLeafSpringCount() {
        return leafSpringCount;
    }

    public void setLeafSpringCount(String leafSpringCount) {
        this.leafSpringCount = leafSpringCount == null ? null : leafSpringCount.trim();
    }

    public String getTireCount() {
        return tireCount;
    }

    public void setTireCount(String tireCount) {
        this.tireCount = tireCount == null ? null : tireCount.trim();
    }

    public String getTireSpec() {
        return tireSpec;
    }

    public void setTireSpec(String tireSpec) {
        this.tireSpec = tireSpec == null ? null : tireSpec.trim();
    }

    public String getFrontGauge() {
        return frontGauge;
    }

    public void setFrontGauge(String frontGauge) {
        this.frontGauge = frontGauge == null ? null : frontGauge.trim();
    }

    public String getBrakeFront() {
        return brakeFront;
    }

    public void setBrakeFront(String brakeFront) {
        this.brakeFront = brakeFront == null ? null : brakeFront.trim();
    }

    public String getBrakeAfter() {
        return brakeAfter;
    }

    public void setBrakeAfter(String brakeAfter) {
        this.brakeAfter = brakeAfter == null ? null : brakeAfter.trim();
    }

    public String getOperationFront() {
        return operationFront;
    }

    public void setOperationFront(String operationFront) {
        this.operationFront = operationFront == null ? null : operationFront.trim();
    }

    public String getOperationAfter() {
        return operationAfter;
    }

    public void setOperationAfter(String operationAfter) {
        this.operationAfter = operationAfter == null ? null : operationAfter.trim();
    }

    public String getSteeringType() {
        return steeringType;
    }

    public void setSteeringType(String steeringType) {
        this.steeringType = steeringType == null ? null : steeringType.trim();
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType == null ? null : startType.trim();
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(String drivingType) {
        this.drivingType = drivingType == null ? null : drivingType.trim();
    }

    public String getBasicStandard() {
        return basicStandard;
    }

    public void setBasicStandard(String basicStandard) {
        this.basicStandard = basicStandard == null ? null : basicStandard.trim();
    }

    public String getChassisBasicStandard() {
        return chassisBasicStandard;
    }

    public void setChassisBasicStandard(String chassisBasicStandard) {
        this.chassisBasicStandard = chassisBasicStandard == null ? null : chassisBasicStandard.trim();
    }

    public String getRearTrack() {
        return rearTrack;
    }

    public void setRearTrack(String rearTrack) {
        this.rearTrack = rearTrack == null ? null : rearTrack.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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