package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopBuilding;


public class TopBuildingVo extends TopBuilding {
private static final long serialVersionUID = 1L;

    /**
     * 所属分区
     */
    private String subareaName;

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }
}
