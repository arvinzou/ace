package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.ErrFeedback;


public class ErrFeedbackQVo extends ErrFeedback {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索关键字
     */
    private String keyword;

    private String nodeCode;

    /**
     * 站点代码
     */
    private String stationCode;

    /**
     * 分区代码
     */
    private String subareaCode;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getSubareaCode() {
        return subareaCode;
    }

    public void setSubareaCode(String subareaCode) {
        this.subareaCode = subareaCode;
    }
}
