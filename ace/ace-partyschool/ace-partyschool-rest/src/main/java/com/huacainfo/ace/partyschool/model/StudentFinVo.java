package com.huacainfo.ace.partyschool.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @Auther: Arvin
 * @Date: 2019/1/11 18:39
 * @Description:
 */
public class StudentFinVo extends BaseModel {
    /**
     * 用户名称
     */
    private String customerName;
    /**
     * 借阅证号
     */
    private String lCardNo;
    /**
     * 打卡时间
     */
    private String dealTime;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getlCardNo() {
        return lCardNo;
    }

    public void setlCardNo(String lCardNo) {
        this.lCardNo = lCardNo;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
