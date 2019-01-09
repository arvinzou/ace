package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.Files;


public class FilesQVo extends Files {
    private static final long serialVersionUID = 1L;
    private  String userId;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
