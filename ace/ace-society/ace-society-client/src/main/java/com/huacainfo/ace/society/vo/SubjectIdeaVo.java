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
     * 议题标题
     */
    private String title;

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
