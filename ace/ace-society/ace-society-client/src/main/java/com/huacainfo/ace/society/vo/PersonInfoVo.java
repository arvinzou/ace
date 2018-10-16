package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.PersonInfo;


public class PersonInfoVo extends PersonInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像
     */
    private String headimgurl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
