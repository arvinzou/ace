package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.society.model.PersonInfo;
import com.huacainfo.ace.society.model.SocietyOrgInfo;

/**
 * @Auther: Arvin
 * @Date: 2018/9/27 11:45
 * @Description:
 */
public class CustomerVo extends BaseModel {
    //身份标识 1 -- 个人/党员 ，2 - 社会/党组织 3-管理员
    private String regType;
    /**
     * 个人/党员
     */
    private PersonInfo person;
    /**
     * 社会/党组织
     */
    private SocietyOrgInfo societyOrg;

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public PersonInfo getPerson() {
        return person;
    }

    public void setPerson(PersonInfo person) {
        this.person = person;
    }

    public SocietyOrgInfo getSocietyOrg() {
        return societyOrg;
    }

    public void setSocietyOrg(SocietyOrgInfo societyOrg) {
        this.societyOrg = societyOrg;
    }
}
