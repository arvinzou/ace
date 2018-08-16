package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CourseCmt;


public class CourseCmtVo extends CourseCmt {
    private static final long serialVersionUID = 1L;

    private String nickname;
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
