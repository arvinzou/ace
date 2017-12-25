package com.huacainfo.ace.rvc.vo;

import com.huacainfo.ace.common.tools.JsonUtil;

import java.util.List;

/**
 * Created by Arvin on 2017/12/21.
 */
public class ConferenceDTO {


    /**
     * 会议标题- 名称
     */
    private String title;

    /***
     * 会议
     */
    private String subtitle;
    /**
     * 主持人id
     */
    private String emceeId;
    /**
     * 主持人名称
     */
    private String emceeName;

    /**
     * 会议开始时间
     */
    private String beginDate;

    /**
     * 会议结束时间
     */
    private String endDate;

    /**
     * 内置会议地址使用 -- 一个地址对应一个科达视讯账号信息
     */
    private String addressId;
    /**
     * 会议地址名称
     */
    private String addressName;

    /**
     * 与会人员信息
     */
    private List<JoinMember> inviteList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getEmceeId() {
        return emceeId;
    }

    public void setEmceeId(String emceeId) {
        this.emceeId = emceeId;
    }

    public String getEmceeName() {
        return emceeName;
    }

    public void setEmceeName(String emceeName) {
        this.emceeName = emceeName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<JoinMember> getInviteList() {
        return inviteList;
    }

    public void setInviteList(List<JoinMember> inviteList) {
        this.inviteList = inviteList;
    }


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
