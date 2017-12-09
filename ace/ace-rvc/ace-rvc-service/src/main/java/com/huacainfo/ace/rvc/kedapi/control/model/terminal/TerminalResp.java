package com.huacainfo.ace.rvc.kedapi.control.model.terminal;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2017/12/4.
 */
public class TerminalResp extends BaseModel {

    /**
     * 终端数组，仅5.1.0.2版本而且是本级会议添加终端时，支持回复带此参数
     */
    private List<Mts> mts;


    public void setMts(List<Mts> mts) {
        this.mts = mts;
    }

    public List<Mts> getMts() {
        return this.mts;
    }
}
