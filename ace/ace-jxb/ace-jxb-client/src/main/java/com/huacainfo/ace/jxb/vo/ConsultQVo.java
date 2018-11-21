package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Consult;

import java.math.BigDecimal;


public class ConsultQVo extends Consult {

    private BigDecimal telephoneCon;
    private BigDecimal wecharCon;
    private BigDecimal facetofaceCon;

    public BigDecimal getTelephoneCon() {
        return telephoneCon;
    }

    public void setTelephoneCon(BigDecimal telephoneCon) {
        this.telephoneCon = telephoneCon;
    }

    public BigDecimal getWecharCon() {
        return wecharCon;
    }

    public void setWecharCon(BigDecimal wecharCon) {
        this.wecharCon = wecharCon;
    }

    public BigDecimal getFacetofaceCon() {
        return facetofaceCon;
    }

    public void setFacetofaceCon(BigDecimal facetofaceCon) {
        this.facetofaceCon = facetofaceCon;
    }

    private static final long serialVersionUID = 1L;
}
