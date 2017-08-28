package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.MemberPayInfo;

public class MemberPayInfoQVo extends MemberPayInfo implements
		java.io.Serializable {

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

	

	private String payType;
	
	private String year;

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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}



	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

}
