package com.huacainfo.ace.live.vo;

import com.huacainfo.ace.live.model.LiveMsg;


public class LiveMsgQVo extends LiveMsg {
    private static final long serialVersionUID = 1L;

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
