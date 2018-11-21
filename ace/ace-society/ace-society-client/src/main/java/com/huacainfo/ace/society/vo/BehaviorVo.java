package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Behavior;

import java.util.List;


public class BehaviorVo extends Behavior {
    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 昵称
     */
    private String nickName;
    private String regType;
    private String unionId;
    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    private List<BehaviorAnnexVo> behaviorAnnexList;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<BehaviorAnnexVo> getBehaviorAnnexList() {
        return behaviorAnnexList;
    }

    public void setBehaviorAnnexList(List<BehaviorAnnexVo> behaviorAnnexList) {
        this.behaviorAnnexList = behaviorAnnexList;
    }
}
