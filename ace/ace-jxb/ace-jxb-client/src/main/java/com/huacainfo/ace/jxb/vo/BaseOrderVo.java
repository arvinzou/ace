package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.ConsultProduct;
import com.huacainfo.ace.jxb.model.Counselor;
//import com.huacainfo.ace.portal.model.Users;


public class BaseOrderVo extends BaseOrder {
    private static final long serialVersionUID = 1L;

    private Counselor counselor;
    private ConsultProduct consultProduct;
    private String consumerName;
    private String consumerImgUrl;

    public Counselor getCounselor() {
        return counselor;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public ConsultProduct getConsultProduct() {
        return consultProduct;
    }

    public void setConsultProduct(ConsultProduct consultProduct) {
        this.consultProduct = consultProduct;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerImgUrl() {
        return consumerImgUrl;
    }

    public void setConsumerImgUrl(String consumerImgUrl) {
        this.consumerImgUrl = consumerImgUrl;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    private User user;
}
