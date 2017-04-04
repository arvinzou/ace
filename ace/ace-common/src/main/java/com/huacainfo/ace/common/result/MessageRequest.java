package com.huacainfo.ace.common.result;

public class MessageRequest implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	String areaId;
	String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
