package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopFinanceProject;

import java.math.BigDecimal;


public class FopFinanceProjectQVo extends FopFinanceProject {
    private static final long serialVersionUID = 1L;

    private BigDecimal btmYield;

    private BigDecimal topYield;

    public BigDecimal getBtmYield() {
        return btmYield;
    }

    public void setBtmYield(BigDecimal btmYield) {
        this.btmYield = btmYield;
    }

    public BigDecimal getTopYield() {
        return topYield;
    }

    public void setTopYield(BigDecimal topYield) {
        this.topYield = topYield;
    }
}
