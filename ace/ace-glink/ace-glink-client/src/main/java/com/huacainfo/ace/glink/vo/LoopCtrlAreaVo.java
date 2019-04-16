package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.LoopCtrlArea;

import java.util.List;


public class LoopCtrlAreaVo extends LoopCtrlArea {

    private String subCode;

    private String subName;

    private List<LoopCtrlArea> loopCtrlAreaList;

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<LoopCtrlArea> getLoopCtrlAreaList() {
        return loopCtrlAreaList;
    }

    public void setLoopCtrlAreaList(List<LoopCtrlArea> loopCtrlAreaList) {
        this.loopCtrlAreaList = loopCtrlAreaList;
    }
}
