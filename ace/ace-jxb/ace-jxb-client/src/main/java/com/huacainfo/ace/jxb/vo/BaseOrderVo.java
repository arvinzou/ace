package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.*;
//import com.huacainfo.ace.portal.model.Users;


public class BaseOrderVo extends BaseOrder {
    private static final long serialVersionUID = 1L;

    private Counselor counselor;
    private String consumerName;
    private String consumerImgUrl;

    /**
     * 咨询订单相关内容
     */
    private ConsultOrder consultOrder;
    private ConsultProduct consultProduct;


    /**
     * 课程订单相关内容
     */
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ConsultOrder getConsultOrder() {
        return consultOrder;
    }

    public void setConsultOrder(ConsultOrder consultOrder) {
        this.consultOrder = consultOrder;
    }

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
