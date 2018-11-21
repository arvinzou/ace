package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SubjectIdea;

import java.io.Serializable;
import java.util.List;


public class SubjectIdeaVo extends SubjectIdea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 用户类型，组织/个人
     */
    private String regType;
    /**
     * 议题标题
     */
    private String title;

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    private List<SubjectIdeaAnnexVo> listSubjectIdeaAnnexVo;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectIdeaAnnexVo> getListSubjectIdeaAnnexVo() {
        return listSubjectIdeaAnnexVo;
    }

    public void setListSubjectIdeaAnnexVo(List<SubjectIdeaAnnexVo> listSubjectIdeaAnnexVo) {
        this.listSubjectIdeaAnnexVo = listSubjectIdeaAnnexVo;
    }
}
