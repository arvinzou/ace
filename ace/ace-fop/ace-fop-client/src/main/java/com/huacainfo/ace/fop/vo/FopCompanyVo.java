
package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopCompany;

import java.util.Date;
import java.util.List;


public class FopCompanyVo extends FopCompany {
    private static final long serialVersionUID = 1L;


    /**
     * */
    private String areaCodeName;

    private String realName;
    /**
     * =======================================
     * 法人信息
     */
    private String lpMobile;

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    private String lpSex;
    private Date lpBirthDt;
    private String lpNativePlace;
    private String lpNationality;
    private String lpPolitical;
    private Date lpRecruitmentDate;
    private String lpEducation;
    private String lpSkillJobTitle;
    private String lpDeptPost;
    private String lpIdentityCard;
    private String lpSocietyPost;

    /**
     * =======================================
     * 第一联系人号码
     */
    private String fpMobile;
    /**
     * =======================================
     * 第二联系人号码
     */
    private String spMobile;

    /**
     * 关联信息
     */
    private FopPersonVo person;

    private List<FopCompanyOrgVo> olist;

    private List<FopCompanyContributionVo> clist;

    public FopPersonVo getPerson() {
        return person;
    }

    public void setPerson(FopPersonVo person) {
        this.person = person;
    }

    public List<FopCompanyOrgVo> getOlist() {
        return olist;
    }

    public void setOlist(List<FopCompanyOrgVo> olist) {
        this.olist = olist;
    }

    public List<FopCompanyContributionVo> getClist() {
        return clist;
    }

    public void setClist(List<FopCompanyContributionVo> clist) {
        this.clist = clist;
    }

    public String getFpMobile() {
        return fpMobile;
    }

    public void setFpMobile(String fpMobile) {
        this.fpMobile = fpMobile;
    }

    public String getSpMobile() {
        return spMobile;
    }

    public void setSpMobile(String spMobile) {
        this.spMobile = spMobile;
    }

    public String getLpMobile() {
        return lpMobile;
    }

    public void setLpMobile(String lpMobile) {
        this.lpMobile = lpMobile;
    }

    public String getLpSex() {
        return lpSex;
    }

    public void setLpSex(String lpSex) {
        this.lpSex = lpSex;
    }

    public Date getLpBirthDt() {
        return lpBirthDt;
    }

    public void setLpBirthDt(Date lpBirthDt) {
        this.lpBirthDt = lpBirthDt;
    }

    public String getLpNativePlace() {
        return lpNativePlace;
    }

    public void setLpNativePlace(String lpNativePlace) {
        this.lpNativePlace = lpNativePlace;
    }

    public String getLpNationality() {
        return lpNationality;
    }

    public void setLpNationality(String lpNationality) {
        this.lpNationality = lpNationality;
    }

    public String getLpPolitical() {
        return lpPolitical;
    }

    public void setLpPolitical(String lpPolitical) {
        this.lpPolitical = lpPolitical;
    }

    public Date getLpRecruitmentDate() {
        return lpRecruitmentDate;
    }

    public void setLpRecruitmentDate(Date lpRecruitmentDate) {
        this.lpRecruitmentDate = lpRecruitmentDate;
    }

    public String getLpEducation() {
        return lpEducation;
    }

    public void setLpEducation(String lpEducation) {
        this.lpEducation = lpEducation;
    }

    public String getLpSkillJobTitle() {
        return lpSkillJobTitle;
    }

    public void setLpSkillJobTitle(String lpSkillJobTitle) {
        this.lpSkillJobTitle = lpSkillJobTitle;
    }

    public String getLpDeptPost() {
        return lpDeptPost;
    }

    public void setLpDeptPost(String lpDeptPost) {
        this.lpDeptPost = lpDeptPost;
    }

    public String getLpIdentityCard() {
        return lpIdentityCard;
    }

    public void setLpIdentityCard(String lpIdentityCard) {
        this.lpIdentityCard = lpIdentityCard;
    }

    public String getLpSocietyPost() {
        return lpSocietyPost;
    }

    public void setLpSocietyPost(String lpSocietyPost) {
        this.lpSocietyPost = lpSocietyPost;
    }
}
