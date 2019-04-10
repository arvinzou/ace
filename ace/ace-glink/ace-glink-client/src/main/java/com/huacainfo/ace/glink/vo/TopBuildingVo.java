package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopBuilding;


public class TopBuildingVo extends TopBuilding {
private static final long serialVersionUID = 1L;

    /**
     * 所属分区
     */
    private String subareaName;

    private int animaCount;

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }

    public int getAnimaCount() {
        return animaCount;
    }

    public void setAnimaCount(int animaCount) {
        this.animaCount = animaCount;
    }
}
