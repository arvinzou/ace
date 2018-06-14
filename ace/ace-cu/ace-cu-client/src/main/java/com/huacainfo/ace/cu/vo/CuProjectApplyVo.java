package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;

import java.util.List;


public class CuProjectApplyVo extends CuProjectApply {
    private static final long serialVersionUID = 1L;

    /**
     * 资料附件列表
     */
    private List<CuProjectApplyRes> resList;

    public List<CuProjectApplyRes> getResList() {
        return resList;
    }

    public void setResList(List<CuProjectApplyRes> resList) {
        this.resList = resList;
    }
}
