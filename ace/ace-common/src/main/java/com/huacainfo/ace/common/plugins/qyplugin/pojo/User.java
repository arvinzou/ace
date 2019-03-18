package com.huacainfo.ace.common.plugins.qyplugin.pojo;

/**
 * @program: ace
 * @description:
 * @author: 🥦003
 * @create: 2019-03-15 13:45
 **/
public class User {

    private String account;

    private String email;

    private String realname;

    private String mobile;

    private String sex;

    private String departname;

    private String deptid;

    private int fingerprint;

    private String card;

    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return this.account;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setRealname(String realname){
        this.realname = realname;
    }
    public String getRealname(){
        return this.realname;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return this.sex;
    }
    public void setDepartname(String departname){
        this.departname = departname;
    }
    public String getDepartname(){
        return this.departname;
    }
    public void setFingerprint(int fingerprint){
        this.fingerprint = fingerprint;
    }
    public int getFingerprint(){
        return this.fingerprint;
    }
    public void setCard(String card){
        this.card = card;
    }
    public String getCard(){
        return this.card;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
}
