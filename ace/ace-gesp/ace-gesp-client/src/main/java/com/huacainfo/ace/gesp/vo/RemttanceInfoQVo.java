package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.RemttanceInfo;


public class RemttanceInfoQVo extends RemttanceInfo {

	  
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;
	
	private String startDate;

	private String endsDate;

	private String chargingItemId;

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

	public String getChargingItemId() {
		return chargingItemId;
	}

	public void setChargingItemId(String chargingItemId) {
		this.chargingItemId = chargingItemId;
	}
	
	
	
}
