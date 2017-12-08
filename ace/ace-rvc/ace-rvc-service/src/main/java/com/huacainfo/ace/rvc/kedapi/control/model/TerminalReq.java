package com.huacainfo.ace.rvc.kedapi.control.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

/**
 * 科达设备终端
 */
public class TerminalReq extends BaseModel {
//    {
//        "mts": [
//        {
//            "account": "2322231",
//                "account_type": 5,
//                "bitrate": 2048,
//                "protocol": 0,
//                "forced_call": 0
//        },
//        {
//            "account": "2322232",
//                "account_type": 5,
//                "bitrate": 2048,
//                "protocol": 0,
//                "forced_call": 0
//        }
//  ]
//    }

    /**
     * 接口返回时，接收字段
     */
    private String mt_id;

    /**
     * 终端E164号,IP或电话号码
     */
    private String account;
    /**
     * 终端类型 5-e164号码；6-电话；7-ip地址；
     */
    private int account_type;
    /**
     * 呼叫码率
     */
    private int bitrate;
    /**
     * 呼叫协议 0-H323；1-SIP；
     */
    private int protocol;
    /**
     * 是否强制呼叫，仅支持终本级会议的情况，默认是0 0-不强呼；1-强呼；
     */
    private int forced_call;


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

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getForced_call() {
        return forced_call;
    }

    public void setForced_call(int forced_call) {
        this.forced_call = forced_call;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }
}