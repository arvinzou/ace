package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Traffic;

import java.util.Date;


public class TrafficQVo extends Traffic {
	private static final long serialVersionUID = 1L;

	private String startTime;

	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
