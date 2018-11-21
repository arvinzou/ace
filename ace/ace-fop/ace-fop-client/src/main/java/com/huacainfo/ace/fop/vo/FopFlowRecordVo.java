
package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopFlowRecord;


public class FopFlowRecordVo extends FopFlowRecord {
    private static final long serialVersionUID = 1L;

    private String requestName;

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
