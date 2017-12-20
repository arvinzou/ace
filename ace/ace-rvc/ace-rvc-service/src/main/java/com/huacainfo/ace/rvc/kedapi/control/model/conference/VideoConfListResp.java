package com.huacainfo.ace.rvc.kedapi.control.model.conference;

import java.util.List;

/**
 * Created by Arvin on 2017/12/13.
 */
public class VideoConfListResp {

    /**
     * 数量
     */
    private int total;
    /**
     * 预约会议列表
     */
    private List<VideoConfResp> confs;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VideoConfResp> getConfs() {
        return confs;
    }

    public void setConfs(List<VideoConfResp> confs) {
        this.confs = confs;
    }
}
