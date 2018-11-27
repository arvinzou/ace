package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.FeedBack;


public class FeedBackQVo extends FeedBack {
    private static final long serialVersionUID = 1L;

    /**
     * 微信昵称
     */
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
