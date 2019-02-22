package com.huacainfo.ace.partyschool.model;

import com.huacainfo.ace.partyschool.vo.EvaluatingVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ace
 * @description:
 * @author: 🥦003
 * @create: 2019-02-21 09:50
 **/
public class EvaluationExport implements Serializable {
    private static final long serialVersionUID = 1L;

    private  String userId;

    private String name;

    private String sex;

    private String mobile;

    private List<EvaluationRstVo> entityCounts;

    private String evaluationContent;
    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<EvaluationRstVo> getEntityCounts() {
        return entityCounts;
    }

    public void setEntityCounts(List<EvaluationRstVo> entityCounts) {
        this.entityCounts = entityCounts;
    }
}
