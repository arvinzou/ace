package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.ConsultProduct;


public class ConsultProductVo extends ConsultProduct {
    private static final long serialVersionUID = 1L;

    private String counselorName;

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }
}
