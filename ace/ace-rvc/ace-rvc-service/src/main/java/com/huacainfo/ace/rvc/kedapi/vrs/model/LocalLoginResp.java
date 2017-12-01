package com.huacainfo.ace.rvc.kedapi.vrs.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

/**
 * Created by Arvin on 2017/11/30.
 */
public class LocalLoginResp extends BaseModel {

    /**
     * 是否是集群环境
     */
    private String isclusterdeploy;
    /**
     * 是否是SSO模式
     * 0-否；
     * 1-是；
     */
    private String isssomode;
    /**
     * 用户权限掩码
     * 1-可下载；
     * 2-可直播；
     * 3-可下载可直播；
     * 4-可点播；
     * 5-可下载可点播；
     * 6-可直播可点播；
     * 7-可下载可直播可点播；
     */
    private int rightmask;

    /**
     * 返回的SSO cookie值
     */
    private String token;
    /**
     * 用户域moid
     */
    private String userdomainmoid;
    /**
     * 用户moid
     */
    private String usermoid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户角色
     * 0-超级管理员；
     * 1-普通管理员；
     * 2-普通用户；
     */
    private int userrole;

    public String getIsclusterdeploy() {
        return isclusterdeploy;
    }

    public void setIsclusterdeploy(String isclusterdeploy) {
        this.isclusterdeploy = isclusterdeploy;
    }

    public String getIsssomode() {
        return isssomode;
    }

    public void setIsssomode(String isssomode) {
        this.isssomode = isssomode;
    }

    public int getRightmask() {
        return rightmask;
    }

    public void setRightmask(int rightmask) {
        this.rightmask = rightmask;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserdomainmoid() {
        return userdomainmoid;
    }

    public void setUserdomainmoid(String userdomainmoid) {
        this.userdomainmoid = userdomainmoid;
    }

    public String getUsermoid() {
        return usermoid;
    }

    public void setUsermoid(String usermoid) {
        this.usermoid = usermoid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserrole() {
        return userrole;
    }

    public void setUserrole(int userrole) {
        this.userrole = userrole;
    }
}
