package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.NoticeStatus;


public class NoticeStatusQVo extends NoticeStatus {
private static final long serialVersionUID = 1L;
 private String nStatus;

    public String getnStatus() {
        return nStatus;
    }

    public void setnStatus(String nStatus) {
        this.nStatus = nStatus;
    }
}
