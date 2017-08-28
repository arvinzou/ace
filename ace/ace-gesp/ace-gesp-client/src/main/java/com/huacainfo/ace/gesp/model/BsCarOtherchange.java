package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆基础信息类
 * 
 * @author yu
 *
 */
public class BsCarOtherchange implements Serializable{

	private String id;     //编号
	private String plateNo;     //当前车牌号
	private String color;     //当前车牌颜色
	private String changeDate;     //变更日期
	private String changeReason;     //变更原因
	private String changeDetails;     //变更事项
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
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
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
