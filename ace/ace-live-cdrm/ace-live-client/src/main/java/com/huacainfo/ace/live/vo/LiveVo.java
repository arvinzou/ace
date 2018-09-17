
package com.huacainfo.ace.live.vo;

import com.huacainfo.ace.live.model.Live;


public class LiveVo extends Live {
    private static final long serialVersionUID = 1L;

    /**
     * 直播下所有报道数目
     */
    private int reportCount;

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }
}
