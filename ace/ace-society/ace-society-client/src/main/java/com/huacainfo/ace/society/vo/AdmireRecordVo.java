package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.AdmireRecord;


public class AdmireRecordVo extends AdmireRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 昵称
     */
    private String nickName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
