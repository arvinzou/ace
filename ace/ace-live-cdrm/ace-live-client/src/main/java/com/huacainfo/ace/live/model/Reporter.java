package com.huacainfo.ace.live.model;

/**
 * Created by chenxiaoke on 2018/1/12.
 */
public class Reporter implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String headimgurl;
    private String nickname;
    private String openid;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
