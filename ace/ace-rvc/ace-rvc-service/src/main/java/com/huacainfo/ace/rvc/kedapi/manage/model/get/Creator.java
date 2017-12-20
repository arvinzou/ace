package com.huacainfo.ace.rvc.kedapi.manage.model.get;

/**
 * 会议发起者
 * Created by Arvin on 2017/12/13.
 */
public class Creator {
    /**
     * 名称 最大字符长度：128个字节
     */
    private String name;

    /**
     * 帐号 最大字符长度：128个字节
     */
    private String account;

    /***
     * 帐号类型 1-moid；4-非系统邮箱；5-e164号码；6-电话；7-ip地址；
     */
    private int account_type;

    /**
     * 座机 最大字符长度：地区区号4位-字符电话16位-字符分机10位字符
     */
    private String telephone;

    /**
     * 手机 最大字符长度：15个字节
     */
    private String mobile;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAccount_type() {
        return this.account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
