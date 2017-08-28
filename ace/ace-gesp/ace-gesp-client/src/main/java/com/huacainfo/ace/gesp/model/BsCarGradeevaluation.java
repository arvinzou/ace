package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆等级评定实体类
 * 
 * @author yu
 *
 */
public class BsCarGradeevaluation implements Serializable{

	private String id;     //编号
	private String plateNo;     //当前车牌号
	private String color;     //当前车牌颜色
	private String checkDate;     //检测评定日期
	private String checkType;     //检测类别
	private String mileageRecord;     //行驶里程记录
	private String otherCheckRemark;     //其他检测/二级维护竣工质量检测
	private String techGrade;     //车辆技术等级
	private String BusTypeAndGrade;     //客车类型及等级
	private String checkCompany;     //评定单位
	private String checkUserName;     //检测登记人签名
	private String checkTicketsNo;     //检测单号
	private String checkResult;     //检测结果
	private String auditResult;     //审核结果
	private String auditUserName;     //审核人
	private String auditDepartment;     //审核单位
	private String auditDate;     //审核日期
	private String remark;     //备注
	
	
	
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
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getMileageRecord() {
		return mileageRecord;
	}
	public void setMileageRecord(String mileageRecord) {
		this.mileageRecord = mileageRecord;
	}
	public String getOtherCheckRemark() {
		return otherCheckRemark;
	}
	public void setOtherCheckRemark(String otherCheckRemark) {
		this.otherCheckRemark = otherCheckRemark;
	}
	public String getTechGrade() {
		return techGrade;
	}
	public void setTechGrade(String techGrade) {
		this.techGrade = techGrade;
	}
	public String getBusTypeAndGrade() {
		return BusTypeAndGrade;
	}
	public void setBusTypeAndGrade(String busTypeAndGrade) {
		BusTypeAndGrade = busTypeAndGrade;
	}
	public String getCheckCompany() {
		return checkCompany;
	}
	public void setCheckCompany(String checkCompany) {
		this.checkCompany = checkCompany;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getCheckTicketsNo() {
		return checkTicketsNo;
	}
	public void setCheckTicketsNo(String checkTicketsNo) {
		this.checkTicketsNo = checkTicketsNo;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	public String getAuditDepartment() {
		return auditDepartment;
	}
	public void setAuditDepartment(String auditDepartment) {
		this.auditDepartment = auditDepartment;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
