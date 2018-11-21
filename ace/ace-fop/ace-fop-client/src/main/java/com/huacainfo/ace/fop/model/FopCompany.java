package com.huacainfo.ace.fop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HuaCai003
 */
public class FopCompany implements Serializable {
    private static final long serialVersionUID = 7680108692110355992L;
    private String id;

    private String departmentId;

    private String creditCode;
    /**
     * 工商联职务
     */
    private String fopPost;

    /**
     * 所属工商联
     */
    private String belongTo;

    /***
     * 0-普通企业 1-团体企业 2-律师事务所 3-银行机构 "4": "个人会员"
     */
    private String companyType;

    private String fullName;

    private String shortName;

    private String companyCode;

    private String companyLogo;

    private String companyLinkUrl;

    private String areaCode;

    private String companyProperty;

    private Date establishDate;

    private Integer crewSize;

    private BigDecimal registeredCapital;

    private BigDecimal fixedAssets;

    private BigDecimal workingCapital;

    private BigDecimal ownSpace;

    private BigDecimal tenancySpace;

    private String personId;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String firstPersonId;

    private String secPersonId;

    private String postcode;

    private String fax;

    private String email;

    private String majorVariety;

    private Integer laborContractNum;

    private String thirdLaborRelation;

    private String socialInsuranceAddr;

    private String socialInsuranceNum;

    private BigDecimal accTaxAmount;

    private BigDecimal yearTaxAmount;

    private String remark;

    /**
     * 0-已删除 1 - 非会员 2 - 会员
     */
    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    public String getFopPost() {
        return fopPost;
    }

    public void setFopPost(String fopPost) {
        this.fopPost = fopPost;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo == null ? null : companyLogo.trim();
    }

    public String getCompanyLinkUrl() {
        return companyLinkUrl;
    }

    public void setCompanyLinkUrl(String companyLinkUrl) {
        this.companyLinkUrl = companyLinkUrl == null ? null : companyLinkUrl.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty == null ? null : companyProperty.trim();
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public BigDecimal getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(BigDecimal fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public BigDecimal getWorkingCapital() {
        return workingCapital;
    }

    public void setWorkingCapital(BigDecimal workingCapital) {
        this.workingCapital = workingCapital;
    }

    public BigDecimal getOwnSpace() {
        return ownSpace;
    }

    public void setOwnSpace(BigDecimal ownSpace) {
        this.ownSpace = ownSpace;
    }

    public BigDecimal getTenancySpace() {
        return tenancySpace;
    }

    public void setTenancySpace(BigDecimal tenancySpace) {
        this.tenancySpace = tenancySpace;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getFirstPersonId() {
        return firstPersonId;
    }

    public void setFirstPersonId(String firstPersonId) {
        this.firstPersonId = firstPersonId == null ? null : firstPersonId.trim();
    }

    public String getSecPersonId() {
        return secPersonId;
    }

    public void setSecPersonId(String secPersonId) {
        this.secPersonId = secPersonId == null ? null : secPersonId.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMajorVariety() {
        return majorVariety;
    }

    public void setMajorVariety(String majorVariety) {
        this.majorVariety = majorVariety == null ? null : majorVariety.trim();
    }

    public Integer getLaborContractNum() {
        return laborContractNum;
    }

    public void setLaborContractNum(Integer laborContractNum) {
        this.laborContractNum = laborContractNum;
    }

    public String getThirdLaborRelation() {
        return thirdLaborRelation;
    }

    public void setThirdLaborRelation(String thirdLaborRelation) {
        this.thirdLaborRelation = thirdLaborRelation == null ? null : thirdLaborRelation.trim();
    }

    public String getSocialInsuranceAddr() {
        return socialInsuranceAddr;
    }

    public void setSocialInsuranceAddr(String socialInsuranceAddr) {
        this.socialInsuranceAddr = socialInsuranceAddr == null ? null : socialInsuranceAddr.trim();
    }

    public String getSocialInsuranceNum() {
        return socialInsuranceNum;
    }

    public void setSocialInsuranceNum(String socialInsuranceNum) {
        this.socialInsuranceNum = socialInsuranceNum == null ? null : socialInsuranceNum.trim();
    }

    public BigDecimal getAccTaxAmount() {
        return accTaxAmount;
    }

    public void setAccTaxAmount(BigDecimal accTaxAmount) {
        this.accTaxAmount = accTaxAmount;
    }

    public BigDecimal getYearTaxAmount() {
        return yearTaxAmount;
    }

    public void setYearTaxAmount(BigDecimal yearTaxAmount) {
        this.yearTaxAmount = yearTaxAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}