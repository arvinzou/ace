package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆业户变更类
 * 
 * @author yu
 *
 */
public class BsCarOwnerchange implements Serializable{
	private String id;    //编号
	private String plateNo;    //当前车牌号
	private String color;    //当前车牌颜色
	private String companyName;    //车主名称
	private String companyLicense;    //经营许可证号
	private String economicType;    //经济类型
	private String managementOrgMode;    //经营组织方式
	private String qualificationLevel;    //资质等级
	private String businessScope;    //经营范围
	private String roadTransportCertificate;    //道路运输证号
	private String businessLine;    //经营线路
	private String transportResources;    //运力来源
	private String secondaryMaintenanceCycle;    //二级维护周期
	private String changeDate;    //注册(变更)日期
	private String remark;    //备注
	
	
	
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
	public String getEconomicType() {
		return economicType;
	}
	public void setEconomicType(String economicType) {
		this.economicType = economicType;
	}
	public String getManagementOrgMode() {
		return managementOrgMode;
	}
	public void setManagementOrgMode(String managementOrgMode) {
		this.managementOrgMode = managementOrgMode;
	}
	public String getQualificationLevel() {
		return qualificationLevel;
	}
	public void setQualificationLevel(String qualificationLevel) {
		this.qualificationLevel = qualificationLevel;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getRoadTransportCertificate() {
		return roadTransportCertificate;
	}
	public void setRoadTransportCertificate(String roadTransportCertificate) {
		this.roadTransportCertificate = roadTransportCertificate;
	}
	public String getBusinessLine() {
		return businessLine;
	}
	public void setBusinessLine(String businessLine) {
		this.businessLine = businessLine;
	}
	public String getTransportResources() {
		return transportResources;
	}
	public void setTransportResources(String transportResources) {
		this.transportResources = transportResources;
	}
	public String getSecondaryMaintenanceCycle() {
		return secondaryMaintenanceCycle;
	}
	public void setSecondaryMaintenanceCycle(String secondaryMaintenanceCycle) {
		this.secondaryMaintenanceCycle = secondaryMaintenanceCycle;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
