package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopSubarea;


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
