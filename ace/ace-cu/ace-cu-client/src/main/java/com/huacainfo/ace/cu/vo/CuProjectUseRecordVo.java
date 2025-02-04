package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuProjectUseRecord;


public class CuProjectUseRecordVo extends CuProjectUseRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 用于项目的 项目名称
     */
    private String useToProjectName;

    /**
     * 用于项目的 项目标题
     */
    private String useToProjectTitle;

    /**
     * 用于项目的 项目描述
     */
    private String useToProjectDesc;


    /**
     * 源项目的 项目名称
     */
    private String projectName;

    /**
     * 源项目的 项目标题
     */
    private String projectTitle;

    /**
     * 源项目的 项目描述
     */
    private String projectDesc;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getUseToProjectTitle() {
        return useToProjectTitle;
    }

    public void setUseToProjectTitle(String useToProjectTitle) {
        this.useToProjectTitle = useToProjectTitle;
    }

    public String getUseToProjectName() {
        return useToProjectName;
    }

    public void setUseToProjectName(String useToProjectName) {
        this.useToProjectName = useToProjectName;
    }

    public String getUseToProjectDesc() {
        return useToProjectDesc;
    }

    public void setUseToProjectDesc(String useToProjectDesc) {
        this.useToProjectDesc = useToProjectDesc;
    }
}
