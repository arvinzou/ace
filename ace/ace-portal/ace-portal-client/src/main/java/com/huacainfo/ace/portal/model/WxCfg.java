package com.huacainfo.ace.portal.model;

public class WxCfg implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String appId;

    private String appScret;

    private String accessToken;

    private Integer expiresIn;

    private String name;
    private String deptId;

    private String type;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppScret() {
        return appScret;
    }

    public void setAppScret(String appScret) {
        this.appScret = appScret == null ? null : appScret.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}