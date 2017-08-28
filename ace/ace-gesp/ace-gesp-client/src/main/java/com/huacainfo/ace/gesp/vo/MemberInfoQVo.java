package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.MemberInfo;

public class MemberInfoQVo extends MemberInfo implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 1L;

	private String name;

	private String startDate;

	private String endsDate;

	private String outDate;

	private String chargingItemId;

	private String payStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndsDate() {
		return endsDate;
	}

	public void setEndsDate(String endsDate) {
		this.endsDate = endsDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getChargingItemId() {
		return chargingItemId;
	}

	public void setChargingItemId(String chargingItemId) {
		this.chargingItemId = chargingItemId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

}
