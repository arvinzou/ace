package com.huacainfo.ace.rvc.kedapi.manage.model.get;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2017/12/13.
 */
public class PreConfListResp extends BaseModel {

    /**
     * 数量
     */
    private int total;
    /**
     * 预约会议列表
     */
    private List<PreConfResp> booked_confs;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PreConfResp> getBooked_confs() {
        return booked_confs;
    }

    public void setBooked_confs(List<PreConfResp> booked_confs) {
        this.booked_confs = booked_confs;
    }
}
