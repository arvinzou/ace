package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RoadSection;


public class RoadSectionQVo extends RoadSection {
    private static final long serialVersionUID = 1L;
    /**
     * 查询类别  1-已采集 ; 0 - 未采集
     */
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
