package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Activity;


public class ActivityQVo extends Activity {
private static final long serialVersionUID = 1L;
private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String ingId;

    public String getIngId() {
        return ingId;
    }

    public void setIngId(String ingId) {
        this.ingId = ingId;
    }
}
