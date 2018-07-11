package com.huacainfo.ace.fundtown.vo;

import com.huacainfo.ace.fundtown.model.VipPublicity;


public class VipPublicityQVo extends VipPublicity {
    private static final long serialVersionUID = 1L;

    /**
     * 企业类型  0-管理结构 1-基金
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
