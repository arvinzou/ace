package com.huacainfo.ace.rvc.kedapi.manage.model.create;

/**
 * Created by Arvin on 2017/12/19.
 */
public class VmpMember {

    /**
     * 帐号 最大字符长度：128个字节 仅当跟随类型为会控指定时才需要输入
     */
    private String name;
    /**
     * 帐号 最大字符长度：128个字节 仅当跟随类型为会控指定时才需要输入
     */
    private String account;
    /**
     * 帐号类型 1-moid；4-非系统邮箱；5-e164号；6-电话；7-ip地址；
     */
    private String account_type;
    /**
     * 跟随类型 1-会控指定；2-发言人跟随；3-管理方跟随；4-会议轮询跟随；7-内容共享跟随；
     */
    private int member_type;
    /**
     * 在画画合成中的位置
     */
    private int chn_idx;


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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int getMember_type() {
        return member_type;
    }

    public void setMember_type(int member_type) {
        this.member_type = member_type;
    }

    public int getChn_idx() {
        return chn_idx;
    }

    public void setChn_idx(int chn_idx) {
        this.chn_idx = chn_idx;
    }
}
