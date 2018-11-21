package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.RelatedLinks;

import java.util.List;


public class RelatedLinksVo extends RelatedLinks {
    private static final long serialVersionUID = 1L;

    private List<RelatedLinksVo> children;

    public List<RelatedLinksVo> getChildren() {
        return children;
    }

    public void setChildren(List<RelatedLinksVo> children) {
        this.children = children;
    }
}
