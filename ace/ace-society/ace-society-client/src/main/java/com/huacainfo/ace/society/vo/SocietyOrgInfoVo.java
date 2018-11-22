package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SocietyOrgInfo;


public class SocietyOrgInfoVo extends SocietyOrgInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 组织管理者用户ID
     */
    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
