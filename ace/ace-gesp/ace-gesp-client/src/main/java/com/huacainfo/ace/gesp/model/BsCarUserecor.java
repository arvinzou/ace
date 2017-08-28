package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆使用记录类
 * 
 * @author yu
 *
 */
public class BsCarUserecor implements Serializable{

	private String id; //编号
	private String plateNo;   //车牌号
	private String color;   //车牌颜色
	private String travelMileage;   //行驶里程（KM）
	private String intervalMileage;   //间隔里程（KM）
	private String fuelConsumption;   //燃油消耗（升）
	private String fuelRation;   //燃油与定额比（升/百公里）
	private String quota;   //定额
	private String remain;   //余
	private String loss;   //亏
	private String useDepartment;   //使用单位
	private String driverName;   //司机姓名
	private String useDate;   //日期
	private String remark;   //备注
	
	
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
	public String getTravelMileage() {
		return travelMileage;
	}
	public void setTravelMileage(String travelMileage) {
		this.travelMileage = travelMileage;
	}
	public String getIntervalMileage() {
		return intervalMileage;
	}
	public void setIntervalMileage(String intervalMileage) {
		this.intervalMileage = intervalMileage;
	}
	public String getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(String fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	public String getFuelRation() {
		return fuelRation;
	}
	public void setFuelRation(String fuelRation) {
		this.fuelRation = fuelRation;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getRemain() {
		return remain;
	}
	public void setRemain(String remain) {
		this.remain = remain;
	}
	public String getLoss() {
		return loss;
	}
	public void setLoss(String loss) {
		this.loss = loss;
	}
	public String getUseDepartment() {
		return useDepartment;
	}
	public void setUseDepartment(String useDepartment) {
		this.useDepartment = useDepartment;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
