
package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.LiveRpt;


public class LiveRptVo extends LiveRpt {
    private static final long serialVersionUID = 1L;

    /**
     * 报道员昵称
     */
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
