package com.huacainfo.ace.society.model;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * 功能描述:  注册portal.users模型
 *
 * @param:
 * @return:
 * @auther: Arvin Zou
 * @date: 2018/9/19 9:40
 */
public class Reg extends BaseModel {

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
