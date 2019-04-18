package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeGatewayState;


public class SeGatewayStateVo extends SeGatewayState {

    /**
     * 配电箱基础数据
     */
    private SeNodeVo seNodeVo;

    public SeNodeVo getSeNodeVo() {
        return seNodeVo;
    }

    public void setSeNodeVo(SeNodeVo seNodeVo) {
        this.seNodeVo = seNodeVo;
    }
}
