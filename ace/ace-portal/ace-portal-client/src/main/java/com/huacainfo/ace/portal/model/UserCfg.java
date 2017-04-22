package com.huacainfo.ace.portal.model;

public class UserCfg implements java.io.Serializable{


    private String id;

    private String userId;

    private String cfgKey;

    private String cfgValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey == null ? null : cfgKey.trim();
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue == null ? null : cfgValue.trim();
    }
}