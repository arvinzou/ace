
package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopAssociation;

import java.util.List;


public class FopAssociationVo extends FopAssociation {
    private static final long serialVersionUID = 1L;

    private List<FopAssMemberVo> list;

    private String realName;

    private String areaCodeName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public List<FopAssMemberVo> getList() {
        return list;
    }

    public void setList(List<FopAssMemberVo> list) {
        this.list = list;
    }

}
