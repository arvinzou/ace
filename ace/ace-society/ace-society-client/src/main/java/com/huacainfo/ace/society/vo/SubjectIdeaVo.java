package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SubjectIdea;

import java.io.Serializable;
import java.util.List;


public class SubjectIdeaVo extends SubjectIdea implements Serializable{

    private static final long serialVersionUID = 1L;


    private List<SubjectIdeaAnnexVo> listSubjectIdeaAnnexVo;

    public List<SubjectIdeaAnnexVo> getListSubjectIdeaAnnexVo() {
        return listSubjectIdeaAnnexVo;
    }

    public void setListSubjectIdeaAnnexVo(List<SubjectIdeaAnnexVo> listSubjectIdeaAnnexVo) {
        this.listSubjectIdeaAnnexVo = listSubjectIdeaAnnexVo;
    }
}
