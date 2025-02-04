package com.huacainfo.ace.common.web.tools;

/**
 * Created by chenxiaoke on 2017/8/16.
 */
public class AccessToken {

    private String access_token;// 获取到的access_token

    private int expires_in; // 有效时间（两个小时，7200s）

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
