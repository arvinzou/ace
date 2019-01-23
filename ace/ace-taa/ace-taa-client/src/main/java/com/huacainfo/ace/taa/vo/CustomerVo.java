package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @Auther: Arvin
 * @Date: 2019/1/21 09:33
 * @Description:
 */
public class CustomerVo extends BaseModel {
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 微信头像
     */
    private String avatarUrl;
    /**
     * 所属单位名称
     */
    private String deptName;
    /**
     * 警号
     */
    private String copNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 事故列表数量
     */
    private String accCount;
    /**
     * 已采集路段数量
     */
    private String sectionCount;

    public String getAccCount() {
        return accCount;
    }

    public void setAccCount(String accCount) {
        this.accCount = accCount;
    }

    public String getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(String sectionCount) {
        this.sectionCount = sectionCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCopNo() {
        return copNo;
    }

    public void setCopNo(String copNo) {
        this.copNo = copNo;
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
}
