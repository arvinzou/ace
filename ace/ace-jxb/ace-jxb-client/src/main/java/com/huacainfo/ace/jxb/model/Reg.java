package com.huacainfo.ace.jxb.model;

/**
 * Created by chenxiaoke on 2018/7/12.
 */
public class Reg implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    private String nickname;

    private String unionId;

    private String mobile;

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
