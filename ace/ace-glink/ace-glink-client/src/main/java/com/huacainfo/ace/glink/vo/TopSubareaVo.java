package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopSubarea;

import java.util.List;


public class TopSubareaVo extends TopSubarea {
    private static final long serialVersionUID = 1L;
    /**
     * 行政区划简称
     */
    private String districtName;
    /**
     * 行政区划全称
     */
    private String districtFullName;

    /**
     * 归属站点列表
     */
    private List<TopStationVo> stationList;

    public List<TopStationVo> getStationList() {
        return stationList;
    }

    public void setStationList(List<TopStationVo> stationList) {
        this.stationList = stationList;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictFullName() {
        return districtFullName;
    }

    public void setDistrictFullName(String districtFullName) {
        this.districtFullName = districtFullName;
    }
}
