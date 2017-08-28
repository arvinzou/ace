package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆主要总成部件更换登记类
 * 
 * @author yu
 *
 */
public class BsCarMainpartschange implements Serializable{
	
	
	private String id;     //编号
	private String plateNo;     //当前车牌号
	private String color;     //当前车牌颜色
	private String changeDate;     //变更日期
	private String checkCompany;     //维修单位
	private String changeDetails;     //更换主要部件名称、型号（规格）及厂名
	private String checkUserName;     //登记人签名
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
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getCheckCompany() {
		return checkCompany;
	}
	public void setCheckCompany(String checkCompany) {
		this.checkCompany = checkCompany;
	}
	public String getChangeDetails() {
		return changeDetails;
	}
	public void setChangeDetails(String changeDetails) {
		this.changeDetails = changeDetails;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
