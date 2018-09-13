package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.cu.model.CuDonateList;

import java.math.BigDecimal;


public class CuDonateListVo extends CuDonateList {
    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 捐助所得积分
     */
    private BigDecimal points;

    /**
     * 微信用户头像
     */
    private String headimgurl;
    /**
     * 微信用户昵称
     */
    private String nickname;

    /**
     * 匿名选项  -- 0-否，1-是
     */
    private String anonymity;

    /**
     * 是否需要收据
     * 0-不需要 1-需要
     */
    private String needReceipt;

    private String consigneeName;

    /**
     * 收货人号码
     */
    private String consigneeMobileNumber;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String consigneeAddr;

    /**
     * 捐款人姓名
     */
    private String donateName;
    /**
     * 捐款人手机号码
     */
    private String mobileNumber;
    /**
     * 捐款单位名称
     */
    private String donatePostName;

    public String getDonateName() {
        return donateName;
    }

    public void setDonateName(String donateName) {
        this.donateName = donateName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDonatePostName() {
        return donatePostName;
    }

    public void setDonatePostName(String donatePostName) {
        this.donatePostName = donatePostName;
    }

    public String getNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(String needReceipt) {
        this.needReceipt = needReceipt;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobileNumber() {
        return consigneeMobileNumber;
    }

    public void setConsigneeMobileNumber(String consigneeMobileNumber) {
        this.consigneeMobileNumber = consigneeMobileNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsigneeAddr() {
        return (StringUtil.isEmpty(getProvince()) ? "" : getProvince())
                + (StringUtil.isEmpty(getCity()) ? "" : getCity())
                + (StringUtil.isEmpty(getDistrict()) ? "" : getDistrict())
                + (StringUtil.isEmpty(getAddress()) ? "" : getAddress());
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
