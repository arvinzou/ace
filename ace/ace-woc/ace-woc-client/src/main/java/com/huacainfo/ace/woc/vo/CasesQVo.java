package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Cases;


public class CasesQVo extends Cases {
    private static final long serialVersionUID = 1L;

    /**
     * search condition
     */
    private String startDate;
    private String endDate;
    private String plateNo;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
}
