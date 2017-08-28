package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆交通事故登记类
 * 
 * @author yu
 *
 */
public class BsCarTrafficaccident implements Serializable{

	private String id;     //编号
	private String plateNo;     //当前车牌号
	private String color;     //当前车牌颜色
	private String occurDate;     //事故发生日期
	private String occurPoint;     //事故发生地点
	private String nature;     //事故性质
	private String responsibility;     //事故责任
	private String accidentDetails;     //事故种类及车辆损坏情况
	private String economicLoss;     //企业直接经济损失(元)
	private String inputUserName;     //登记人签名
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
	public String getOccurDate() {
		return occurDate;
	}
	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}
	public String getOccurPoint() {
		return occurPoint;
	}
	public void setOccurPoint(String occurPoint) {
		this.occurPoint = occurPoint;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getAccidentDetails() {
		return accidentDetails;
	}
	public void setAccidentDetails(String accidentDetails) {
		this.accidentDetails = accidentDetails;
	}
	public String getEconomicLoss() {
		return economicLoss;
	}
	public void setEconomicLoss(String economicLoss) {
		this.economicLoss = economicLoss;
	}
	public String getInputUserName() {
		return inputUserName;
	}
	public void setInputUserName(String inputUserName) {
		this.inputUserName = inputUserName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
