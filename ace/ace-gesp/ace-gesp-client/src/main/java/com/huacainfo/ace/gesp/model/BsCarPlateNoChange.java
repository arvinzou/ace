package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆业户变更类
 * 
 * @author yu
 *
 */
public class BsCarPlateNoChange implements Serializable{
	private String id;    //编号
	private String plateNo;    //当前车牌号
	private String color;    //当前车牌颜色
	private String historyPlateNo;    //车主名称
	private String historycolor;    //经营许可证号
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
	
	public String getHistoryPlateNo() {
		return historyPlateNo;
	}
	public void setHistoryPlateNo(String historyPlateNo) {
		this.historyPlateNo = historyPlateNo;
	}
	public String getHistorycolor() {
		return historycolor;
	}
	public void setHistorycolor(String historycolor) {
		this.historycolor = historycolor;
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
