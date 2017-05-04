
package com.huacainfo.ace.operana.vo;

import com.huacainfo.ace.operana.model.Meeting;


public class MeetingVo extends Meeting {
	private static final long serialVersionUID = 1L;
	private String ownerName;
	private String divisionName;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
}
