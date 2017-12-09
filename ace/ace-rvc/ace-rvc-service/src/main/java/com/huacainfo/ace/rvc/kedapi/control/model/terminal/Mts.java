package com.huacainfo.ace.rvc.kedapi.control.model.terminal;

/**
 * Created by Arvin on 2017/12/4.
 */
public class Mts {


    /**
     * 终端E164号,IP或电话号码
     */
    private String account;

    /**
     * 终端类型 5-e164号码；6-电话；7-ip地址；
     */
    private int account_type;

    /**
     * 呼叫协议，仅终端类型为IP地址时有效 0-H323；1-SIP；
     */
    private int protocol;

    /***
     * 终端id
     */
    private int mt_id;

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }

    public int getAccount_type() {
        return this.account_type;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getProtocol() {
        return this.protocol;
    }

    public void setMt_id(int mt_id) {
        this.mt_id = mt_id;
    }

    public int getMt_id() {
        return this.mt_id;
    }
}
