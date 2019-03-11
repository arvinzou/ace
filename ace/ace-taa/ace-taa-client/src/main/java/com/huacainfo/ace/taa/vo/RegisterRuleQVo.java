package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RegisterRule;


public class RegisterRuleQVo extends RegisterRule {
    private static final long serialVersionUID = 1L;
    private String keyWord;

    /**
     * 是否已注册进入系统 0-否，1-是
     */
    private String regStatus;


    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
