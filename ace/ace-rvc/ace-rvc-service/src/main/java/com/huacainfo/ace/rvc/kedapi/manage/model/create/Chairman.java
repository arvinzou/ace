package com.huacainfo.ace.rvc.kedapi.manage.model.create;

/**
 * 主席 -- 可选
 * Created by Arvin on 2017/12/4.
 */
public class Chairman {

    public Chairman() {
    }

    public Chairman(String name, String account, int account_type) {
        this.name = name;
        this.account = account;
        this.account_type = account_type;
    }

    /**
     * 管理方名称 最大字符长度：128个字节
     */
    private String name;

    /**
     * 帐号 最大字符长度：128个字节
     */
    private String account;

    /**
     * 帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
     */
    private int account_type;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return this.account;
    }
    public void setAccount_type(int account_type){
        this.account_type = account_type;
    }
    public int getAccount_type(){
        return this.account_type;
    }
}
