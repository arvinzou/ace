
package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopCompany;
import com.huacainfo.ace.fop.model.FopCompanyAnnex;

import java.util.Date;
import java.util.List;
import java.util.Map;


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
    /**
     * 法人代表信息
     */
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
    private String lpResume;
    private String lpAchievement;
    /**
     * 党组织建立情况
     */
    private String partyCategory;//党组织类型
    private Date partyCrtDt;//党组织成立时间
    private int partyPeoples;//党员人数
    private String partyDutyMan;//党组织负责人
    private String partyPhone;//联系电话
    /**
     * 工会组织建立情况
     */
    private Date unionCrtDt;//工会组织成立时间
    private String unionDutyMan;//工会组织负责人
    private String unionPhone;//联系电话
    /**
     * 对社会公益事业做过何种贡献
     */
    private String reemployContribution;//安排下岗职工再就业
    private String educationContribution;//助学兴教
    private String helpPoorContribution;//帮困扶贫
    private String otherContribution;//其  他
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
    private String companyPropertyName;
    /**
     * 关联信息
     */
    private FopPersonVo person;
    private List<FopCompanyOrgVo> olist;
    private List<FopCompanyContributionVo> clist;

    /**
     * 市工商局_企业变更记录信息
     */
    private Map<String, Object> sgsj;
    /**
     * 市地税局_税务登记信息
     */
    private Map<String, Object> guosj;
    /**
     * 市人社局_企业养老保险单位参保信息
     */
    private Map<String, Object> srsj;

    /**
     * 电子表格 electronic spreadsheet
     */
    private List<FopCompanyAnnex> esAnnex;
    /**
     * 2-同心助学   Concentric study
     */
    private List<FopCompanyAnnex> csAnnex;
    /**
     * 3-精准扶贫 poverty alleviation
     */
    private List<FopCompanyAnnex> paAnnex;
    /**
     * 4-企业党建 Enterprise party building
     */
    private List<FopCompanyAnnex> epbAnnex;

    public List<FopCompanyAnnex> getEsAnnex() {
        return esAnnex;
    }

    public void setEsAnnex(List<FopCompanyAnnex> esAnnex) {
        this.esAnnex = esAnnex;
    }

    public List<FopCompanyAnnex> getCsAnnex() {
        return csAnnex;
    }

    public void setCsAnnex(List<FopCompanyAnnex> csAnnex) {
        this.csAnnex = csAnnex;
    }

    public List<FopCompanyAnnex> getPaAnnex() {
        return paAnnex;
    }

    public void setPaAnnex(List<FopCompanyAnnex> paAnnex) {
        this.paAnnex = paAnnex;
    }

    public List<FopCompanyAnnex> getEpbAnnex() {
        return epbAnnex;
    }

    public void setEpbAnnex(List<FopCompanyAnnex> epbAnnex) {
        this.epbAnnex = epbAnnex;
    }

    public Map<String, Object> getSgsj() {
        return sgsj;
    }

    public void setSgsj(Map<String, Object> sgsj) {
        this.sgsj = sgsj;
    }

    public Map<String, Object> getGuosj() {
        return guosj;
    }

    public void setGuosj(Map<String, Object> guosj) {
        this.guosj = guosj;
    }

    public Map<String, Object> getSrsj() {
        return srsj;
    }

    public void setSrsj(Map<String, Object> srsj) {
        this.srsj = srsj;
    }

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

    public String getCompanyPropertyName() {
        return companyPropertyName;
    }

    public void setCompanyPropertyName(String companyPropertyName) {
        this.companyPropertyName = companyPropertyName;
    }

    public String getPartyCategory() {
        return partyCategory;
    }

    public void setPartyCategory(String partyCategory) {
        this.partyCategory = partyCategory;
    }

    public Date getPartyCrtDt() {
        return partyCrtDt;
    }

    public void setPartyCrtDt(Date partyCrtDt) {
        this.partyCrtDt = partyCrtDt;
    }

    public int getPartyPeoples() {
        return partyPeoples;
    }

    public void setPartyPeoples(int partyPeoples) {
        this.partyPeoples = partyPeoples;
    }

    public String getPartyDutyMan() {
        return partyDutyMan;
    }

    public void setPartyDutyMan(String partyDutyMan) {
        this.partyDutyMan = partyDutyMan;
    }

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public Date getUnionCrtDt() {
        return unionCrtDt;
    }

    public void setUnionCrtDt(Date unionCrtDt) {
        this.unionCrtDt = unionCrtDt;
    }

    public String getUnionDutyMan() {
        return unionDutyMan;
    }

    public void setUnionDutyMan(String unionDutyMan) {
        this.unionDutyMan = unionDutyMan;
    }

    public String getUnionPhone() {
        return unionPhone;
    }

    public void setUnionPhone(String unionPhone) {
        this.unionPhone = unionPhone;
    }

    public String getReemployContribution() {
        return reemployContribution;
    }

    public void setReemployContribution(String reemployContribution) {
        this.reemployContribution = reemployContribution;
    }

    public String getEducationContribution() {
        return educationContribution;
    }

    public void setEducationContribution(String educationContribution) {
        this.educationContribution = educationContribution;
    }

    public String getHelpPoorContribution() {
        return helpPoorContribution;
    }

    public void setHelpPoorContribution(String helpPoorContribution) {
        this.helpPoorContribution = helpPoorContribution;
    }

    public String getOtherContribution() {
        return otherContribution;
    }

    public void setOtherContribution(String otherContribution) {
        this.otherContribution = otherContribution;
    }

    public String getLpResume() {
        return lpResume;
    }

    public void setLpResume(String lpResume) {
        this.lpResume = lpResume;
    }

    public String getLpAchievement() {
        return lpAchievement;
    }

    public void setLpAchievement(String lpAchievement) {
        this.lpAchievement = lpAchievement;
    }

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
