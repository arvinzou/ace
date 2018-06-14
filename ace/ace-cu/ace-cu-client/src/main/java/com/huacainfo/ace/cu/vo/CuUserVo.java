package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuUser;


public class CuUserVo extends CuUser {
    private static final long serialVersionUID = 1L;

    /**
     * 微信头像
     */
    private String headimgurl;

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
