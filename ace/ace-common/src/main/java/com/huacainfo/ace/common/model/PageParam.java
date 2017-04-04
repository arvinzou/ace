package com.huacainfo.ace.common.model;

import java.io.Serializable;

import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;

public class PageParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private int start;
	private int limit;
	private int totalRecord;
	private int page;
	public String orderBy;
	private String sord;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		if (limit <= 0) {
			limit = CommonKeys.GRID_DEFAULT_PAGE_SIZE;
		}
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderBy() {
		if(!CommonUtils.isBlank(this.orderBy)){
			return CommonUtils.propertyToField(orderBy)+" "+this.getSord();
		}
		return null;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
	
}
