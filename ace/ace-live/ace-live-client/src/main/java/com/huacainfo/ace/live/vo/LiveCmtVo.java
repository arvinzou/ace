
package com.huacainfo.ace.live.vo;

import com.huacainfo.ace.live.model.LiveCmt;


public class LiveCmtVo extends LiveCmt {
    private static final long serialVersionUID = 1L;

    private String headimgurl;

    private String nickname;

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
}
