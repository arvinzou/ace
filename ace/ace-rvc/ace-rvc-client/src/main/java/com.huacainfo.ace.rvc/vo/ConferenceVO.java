package com.huacainfo.ace.rvc.vo;

import com.huacainfo.ace.rvc.model.RvcConference;

import java.util.List;

/**
 * Created by Arvin on 2017/12/20.
 */
public class ConferenceVO {
    private int total;
    private int pageNo;
    private int pageSize;

    private List<RvcConference> rows;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<RvcConference> getRows() {
        return rows;
    }

    public void setRows(List<RvcConference> rows) {
        this.rows = rows;
    }
}
