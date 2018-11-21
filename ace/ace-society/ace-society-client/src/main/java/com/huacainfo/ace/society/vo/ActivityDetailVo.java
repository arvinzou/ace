package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.model.ActivityDetail;
import com.huacainfo.ace.society.model.CoinConfig;


public class ActivityDetailVo extends ActivityDetail {
private static final long serialVersionUID = 1L;

    private String name;

    private String mobile;

    private String headimgurl;

    private String nickname;


    private Activity activity;

    private CoinConfig coinConfig;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public CoinConfig getCoinConfig() {
        return coinConfig;
    }

    public void setCoinConfig(CoinConfig coinConfig) {
        this.coinConfig = coinConfig;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
