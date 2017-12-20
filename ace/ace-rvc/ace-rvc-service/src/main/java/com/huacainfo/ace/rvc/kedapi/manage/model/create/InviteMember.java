package com.huacainfo.ace.rvc.kedapi.manage.model.create;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

/**
 * Created by Arvin on 2017/12/11.
 */
public class InviteMember extends BaseModel {
    private String name;//str	名称 最大字符长度：128个字节
    private String account;//str	帐号 最大字符长度：128个字节
    private int account_type;//	int	帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；

    public InviteMember(String name, String account, int account_type) {
        this.name = name;
        this.account = account;
        this.account_type = account_type;

    }

    public InviteMember() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAccount_type() {
        return account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }
}
