package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopLoanProduct;

import java.math.BigDecimal;


public class FopLoanProductQVo extends FopLoanProduct {
    private static final long serialVersionUID = 1L;

    private BigDecimal btmRate;

    private BigDecimal topRate;

    private BigDecimal btmAmount;
    private BigDecimal topAmount;

    public BigDecimal getBtmAmount() {
        return btmAmount;
    }

    public void setBtmAmount(BigDecimal btmAmount) {
        this.btmAmount = btmAmount;
    }

    public BigDecimal getTopAmount() {
        return topAmount;
    }

    public void setTopAmount(BigDecimal topAmount) {
        this.topAmount = topAmount;
    }

    public BigDecimal getBtmRate() {
        return btmRate;
    }

    public void setBtmRate(BigDecimal btmRate) {
        this.btmRate = btmRate;
    }

    public BigDecimal getTopRate() {
        return topRate;
    }

    public void setTopRate(BigDecimal topRate) {
        this.topRate = topRate;
    }


}
