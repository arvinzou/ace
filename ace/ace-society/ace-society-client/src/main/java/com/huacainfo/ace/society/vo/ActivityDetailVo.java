package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.ActivityDetail;


public class ActivityDetailVo extends ActivityDetail {
private static final long serialVersionUID = 1L;

    private String name;

    private String mobile;

    private String headimgurl;

    private String nickname;

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
