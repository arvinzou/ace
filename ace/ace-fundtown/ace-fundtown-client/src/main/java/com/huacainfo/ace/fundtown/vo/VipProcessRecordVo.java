package com.huacainfo.ace.fundtown.vo;

import com.huacainfo.ace.fundtown.model.VipProcessRecord;


public class VipProcessRecordVo extends VipProcessRecord {
    private static final long serialVersionUID = 1L;

    private String nodeName;
    private String nodeIndex;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(String nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}
