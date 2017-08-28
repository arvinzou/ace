package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆维修信息类
 * 
 * @author yu
 *
 */
public class BsCarRepair implements Serializable{
	private String id;      //编号
	private String plateNo;      //当前车牌号
	private String color;      //当前车牌颜色
	private String repairDate;      //维修日期
	private String repairType;      //维修类别
	private String repairCompany;      //维修单位
	private String repairUserName;      //维修登记人签名
	private String repairTicketsNo;      //检测单号
	private String repairResult;      //检测结果
	private String auditResult;      //审核结果
	private String auditUserName;      //审核人
	private String auditDepartment;      //审核单位
	private String auditDate;      //审核日期
	private String remark;      //备注
	
	
	
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
	public String getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	public String getRepairType() {
		return repairType;
	}
	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}
	public String getRepairCompany() {
		return repairCompany;
	}
	public void setRepairCompany(String repairCompany) {
		this.repairCompany = repairCompany;
	}
	public String getRepairUserName() {
		return repairUserName;
	}
	public void setRepairUserName(String repairUserName) {
		this.repairUserName = repairUserName;
	}
	public String getRepairTicketsNo() {
		return repairTicketsNo;
	}
	public void setRepairTicketsNo(String repairTicketsNo) {
		this.repairTicketsNo = repairTicketsNo;
	}
	public String getRepairResult() {
		return repairResult;
	}
	public void setRepairResult(String repairResult) {
		this.repairResult = repairResult;
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
