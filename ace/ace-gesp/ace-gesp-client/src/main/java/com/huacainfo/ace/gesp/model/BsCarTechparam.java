package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆业户变更类
 * 
 * @author yu
 *
 */
public class BsCarTechparam implements Serializable{
	
	private String id;      //编号
	private String plateNo;      //车牌号
	private String color;      //车牌颜色
	private String carTinyType;      //车辆细类型
	private String dateOrigin;      //车辆出厂日期/产地
	private String vin;      //VIN(或车架)号
	private String chassisBrandModel;      //底盘厂牌型号
	private String busTypeClass;      //客车类型等级
	private String dimensions;      //车辆外廓尺寸
	private String totalMass;      //总质量
	private String seatBerthArrange;      //座/铺位排列
	private String approvedQualityCrew;      //核定载质量/乘员数
	private String approvedTotalMass;      //核定牵引总质量
	private String axlesNumber;      //车轴数/驱动轴数
	private String engineBrandModel;      //发动机厂牌型号
	private String engineNumber;      //发动机号码
	private String fuelType;      //燃料种类
	private String enginePower;      //发动机功率
	private String engineDisplacement;      //发动机排量
	private String emissionStandard;      //排放标准
	private String driveForm;      //驱动形式
	private String tyreNumberSpeci;      //轮胎数/规格
	private String headlamp;      //前照灯制式
	private String transmissionType;      //变速器形式
	private String retarder;      //缓速器
	private String diverter;      //转向器
	private String brakeForm;      //行车制动形式
	private String SuspensionForm;      //悬挂形式
	private String otherConfig;      //其他配置
	private String remark;      //备注
	private String datafrom;      //数据来源[1=企业录入,2=word采集,9=网站爬取]
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getCarTinyType() {
		return carTinyType;
	}
	public void setCarTinyType(String carTinyType) {
		this.carTinyType = carTinyType;
	}
	public String getDateOrigin() {
		return dateOrigin;
	}
	public void setDateOrigin(String dateOrigin) {
		this.dateOrigin = dateOrigin;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getChassisBrandModel() {
		return chassisBrandModel;
	}
	public void setChassisBrandModel(String chassisBrandModel) {
		this.chassisBrandModel = chassisBrandModel;
	}
	public String getBusTypeClass() {
		return busTypeClass;
	}
	public void setBusTypeClass(String busTypeClass) {
		this.busTypeClass = busTypeClass;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	public String getTotalMass() {
		return totalMass;
	}
	public void setTotalMass(String totalMass) {
		this.totalMass = totalMass;
	}
	public String getSeatBerthArrange() {
		return seatBerthArrange;
	}
	public void setSeatBerthArrange(String seatBerthArrange) {
		this.seatBerthArrange = seatBerthArrange;
	}
	public String getApprovedQualityCrew() {
		return approvedQualityCrew;
	}
	public void setApprovedQualityCrew(String approvedQualityCrew) {
		this.approvedQualityCrew = approvedQualityCrew;
	}
	public String getApprovedTotalMass() {
		return approvedTotalMass;
	}
	public void setApprovedTotalMass(String approvedTotalMass) {
		this.approvedTotalMass = approvedTotalMass;
	}
	public String getAxlesNumber() {
		return axlesNumber;
	}
	public void setAxlesNumber(String axlesNumber) {
		this.axlesNumber = axlesNumber;
	}
	public String getEngineBrandModel() {
		return engineBrandModel;
	}
	public void setEngineBrandModel(String engineBrandModel) {
		this.engineBrandModel = engineBrandModel;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(String enginePower) {
		this.enginePower = enginePower;
	}
	public String getEngineDisplacement() {
		return engineDisplacement;
	}
	public void setEngineDisplacement(String engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}
	public String getEmissionStandard() {
		return emissionStandard;
	}
	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard = emissionStandard;
	}
	public String getDriveForm() {
		return driveForm;
	}
	public void setDriveForm(String driveForm) {
		this.driveForm = driveForm;
	}
	public String getTyreNumberSpeci() {
		return tyreNumberSpeci;
	}
	public void setTyreNumberSpeci(String tyreNumberSpeci) {
		this.tyreNumberSpeci = tyreNumberSpeci;
	}
	public String getHeadlamp() {
		return headlamp;
	}
	public void setHeadlamp(String headlamp) {
		this.headlamp = headlamp;
	}
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getRetarder() {
		return retarder;
	}
	public void setRetarder(String retarder) {
		this.retarder = retarder;
	}
	public String getDiverter() {
		return diverter;
	}
	public void setDiverter(String diverter) {
		this.diverter = diverter;
	}
	public String getBrakeForm() {
		return brakeForm;
	}
	public void setBrakeForm(String brakeForm) {
		this.brakeForm = brakeForm;
	}
	public String getSuspensionForm() {
		return SuspensionForm;
	}
	public void setSuspensionForm(String suspensionForm) {
		SuspensionForm = suspensionForm;
	}
	public String getOtherConfig() {
		return otherConfig;
	}
	public void setOtherConfig(String otherConfig) {
		this.otherConfig = otherConfig;
	}
	public String getDatafrom() {
		return datafrom;
	}
	public void setDatafrom(String datafrom) {
		this.datafrom = datafrom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
