package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CoursePart;
import com.huacainfo.ace.jxb.model.CourseSource;

import java.util.List;


public class CoursePartVo extends CoursePart {
    private static final long serialVersionUID = 1L;
    /**
     * 章节资源列表
     */
    List<CourseSource> sourceList;

    public List<CourseSource> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<CourseSource> sourceList) {
        this.sourceList = sourceList;
    }
}
