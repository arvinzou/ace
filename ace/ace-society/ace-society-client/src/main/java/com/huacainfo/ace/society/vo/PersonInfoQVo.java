package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.PersonInfo;


public class PersonInfoQVo extends PersonInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 所属组织名称
     */
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
