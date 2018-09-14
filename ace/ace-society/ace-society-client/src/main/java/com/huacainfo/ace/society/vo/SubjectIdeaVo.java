package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SubjectIdea;

import java.io.Serializable;
import java.util.List;


public class SubjectIdeaVo extends SubjectIdea implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 议题标题
     */
    private String title;

    private List<SubjectIdeaAnnexVo> listSubjectIdeaAnnexVo;

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
