
package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.EvaluatData;


public class EvaluatDataVo extends EvaluatData {
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

	private static final long serialVersionUID = 1L;
}
