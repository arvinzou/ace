package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.AnimaLnk;


public class AnimaLnkVo extends AnimaLnk {
    private static final long serialVersionUID = 1L;

    /**
     * 分区编码
     */
    private String subareaCode;

    /**
     * 分区名称
     */
    private String subareaName;

    /**
     * 建筑物名称
     */
    private String topBuildingName;

    /**
     * 建筑物地址
     */
    private String address;

    /**
     * 节目数量
     */
    private int animaCount;

    /**
     * 媒体文件对象
     */
    private AnimaResVo animaResVo;

    public String getSubareaCode() {
        return subareaCode;
    }
    public void setSubareaCode(String subareaCode) {
        this.subareaCode = subareaCode;
    }

    public String getSubareaName() {
        return subareaName;
    }

    public void setSubareaName(String subareaName) {
        this.subareaName = subareaName;
    }

    public String getTopBuildingName() {
        return topBuildingName;
    }

    public void setTopBuildingName(String topBuildingName) {
        this.topBuildingName = topBuildingName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAnimaCount() {
        return animaCount;
    }

    public void setAnimaCount(int animaCount) {
        this.animaCount = animaCount;
    }

    public AnimaResVo getAnimaResVo() {
        return animaResVo;
    }

    public void setAnimaResVo(AnimaResVo animaResVo) {
        this.animaResVo = animaResVo;
    }
}
