package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.CardData;


public class CardDataQVo extends CardData {
    private static final long serialVersionUID = 1L;

    /**
     * 注册类型： student-学员    teacher-教职工
     */
    private String regType;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 身份证号码
     */
    private String idCard;

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getName() {
        return name == null ? null : name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile == null ? null : mobile.trim();
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard == null ? null : idCard.trim();
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
