package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.model.ConsultProduct;

import java.util.List;


public class ConsultVo extends Consult {
    private static final long serialVersionUID = 1L;

    private List<ConsultProduct> productList;

    public List<ConsultProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ConsultProduct> productList) {
        this.productList = productList;
    }
}
