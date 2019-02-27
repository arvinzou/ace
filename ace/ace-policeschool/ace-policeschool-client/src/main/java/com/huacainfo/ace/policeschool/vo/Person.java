package com.huacainfo.ace.policeschool.vo;

/**
 * Created by chenxiaoke on 2019/1/12.
 */
public class Person implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String mobile;

    private String headimgurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
