package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.TopBuilding;


public class TopBuildingVo extends TopBuilding {
private static final long serialVersionUID = 1L;

    /**
     * 该建筑对应的站点
     */
    private TopStationVo topStationVo;
    /**
     * 建筑物下面的媒体文件统计
     */
    private int animaCount;

    public int getAnimaCount() {
        return animaCount;
    }

    public void setAnimaCount(int animaCount) {
        this.animaCount = animaCount;
    }

    public TopStationVo getTopStationVo() {
        return topStationVo;
    }

    public void setTopStationVo(TopStationVo topStationVo) {
        this.topStationVo = topStationVo;
    }
}
