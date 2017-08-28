package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆基础信息类
 * 
 * @author yu
 *
 */
public class BsCarBase implements Serializable{

	private String id; //编号
	private String collectSiteCarId;   //采集网站给车辆的ID
	private String companyName;   //企业名称
	private String companyLicense;   //企业经营许可证号
	private String plateNo;   //车牌号
	private String color;   //车牌颜色
	private String carBigType;   //车辆大类型
	private String brandName;   //车辆厂牌
	private String brandModel;   //车辆厂牌型号
	private String approvedTon;   //车辆核定吨位
	private String checkValidityDate;   //车辆审验有效期
	private String changeDate;   //注册(变更)日期
	private String roadTransportCertificate;   //车辆道路运输证号
	private String managementStatus;   //车辆经营状态
	private String managementScope;   //车辆经营范围
	private String carAddress;   //车辆地址
	private String remark;   //备注
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollectSiteCarId() {
		return collectSiteCarId;
	}
	public void setCollectSiteCarId(String collectSiteCarId) {
		this.collectSiteCarId = collectSiteCarId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLicense() {
		return companyLicense;
	}
	public void setCompanyLicense(String companyLicense) {
		this.companyLicense = companyLicense;
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
	public String getCarBigType() {
		return carBigType;
	}
	public void setCarBigType(String carBigType) {
		this.carBigType = carBigType;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandModel() {
		return brandModel;
	}
	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}
	public String getApprovedTon() {
		return approvedTon;
	}
	public void setApprovedTon(String approvedTon) {
		this.approvedTon = approvedTon;
	}
	public String getCheckValidityDate() {
		return checkValidityDate;
	}
	public void setCheckValidityDate(String checkValidityDate) {
		this.checkValidityDate = checkValidityDate;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getRoadTransportCertificate() {
		return roadTransportCertificate;
	}
	public void setRoadTransportCertificate(String roadTransportCertificate) {
		this.roadTransportCertificate = roadTransportCertificate;
	}
	public String getManagementStatus() {
		return managementStatus;
	}
	public void setManagementStatus(String managementStatus) {
		this.managementStatus = managementStatus;
	}
	public String getManagementScope() {
		return managementScope;
	}
	public void setManagementScope(String managementScope) {
		this.managementScope = managementScope;
	}
	public String getCarAddress() {
		return carAddress;
	}
	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
