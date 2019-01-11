package com.huacainfo.ace.partyschool.model;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;

/**
 * @Auther: Arvin
 * @Date: 2019/1/10 15:58
 * @Description:
 */
public class AttResultVo extends BaseModel {
    /**
     * 用户姓名
     */
    private String customerName;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 账户编码
     */
    private String accountId;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 终端机编号
     */
    private String termNo;
    /**
     * 终端机名称
     */
    private String termName;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
