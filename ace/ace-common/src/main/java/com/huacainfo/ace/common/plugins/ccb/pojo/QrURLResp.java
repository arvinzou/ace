package com.huacainfo.ace.common.plugins.ccb.pojo;

import java.io.Serializable;

public class QrURLResp implements Serializable {
    private static final long serialVersionUID = 1L;

    private String SUCCESS;
    private String PAYURL;

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String success) {
        SUCCESS = success;
    }

    public String getPAYURL() {
        return PAYURL;
    }

    public void setPAYURL(String payurl) {
        PAYURL = payurl;
    }
}
