package com.huacainfo.ace.jxb.model;

import java.math.BigDecimal;
import java.util.Date;

public class BaseOrder {
    private String id;

    /**
     * 客户主键
     */
    private String consumerId;
    /**
     * 商品主键
     */
    private String commodityId;
    /**
     * 订单类型 1-咨询订单 2-课程订单
     */
    private String category;
    /**
     * 商家主键
     */
    private String businessId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商家名称
     */
    private String businessName;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 付款金额
     */
    private BigDecimal payMoney;
    /**
     * 支付状态 1-待支付 2-已付款(待接单) 3-申请退款 4-已退款 6-咨询（课程）结束/待评价 7-已完结 8-自动关闭
     */
    private String payStatus;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 入库时间
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId == null ? null : consumerId.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}