package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Department implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1007838995037364699L;

	
	private String departmentId;

    private String parentDepartmentId;

    private String departmentName;

    private String shortName;

    private String category;

    private String areaCode;

    private String pcode;

    private String contactName;

    private String contactTel;

    private String contactMobile;

    private String contactQq;

    private String contactEmail;

    private String contactFax;

    private String legalPersonName;

    private String legalPersonIdType;

    private String legalPersonIdNo;

    private String legalPersonTel;

    private String legalPersonMobile;

    private String legalPersonAddr;

    private Date regDate;

    private String regCapital;

    private String regAreaCode;

    private String regAddr;

    private String nature;

    private String bussLicenseNo;

    private String bussAddr;

    private String bussAreaCode;

    private String fax;

    private String email;

    private String transDepartApprovalNo;

    private String transBussLicenseNo;

    private Date transBussLicenseValidDate;

    private String transBussScope;

    private Integer employeesNum;

    private Integer driverNum;

    private Integer unlicensedDriverNum;

    private Integer licensedDriverNum;

    private String status;

    private String type;

    private String simage;

    private String bimage;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private String lastModifyUserName;
    private String accidentOfYear;

    private String complaintsRemark;
    
    private String checkCode;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(String parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactQq() {
		return contactQq;
	}

	public void setContactQq(String contactQq) {
		this.contactQq = contactQq;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getLegalPersonIdType() {
		return legalPersonIdType;
	}

	public void setLegalPersonIdType(String legalPersonIdType) {
		this.legalPersonIdType = legalPersonIdType;
	}

	public String getLegalPersonIdNo() {
		return legalPersonIdNo;
	}

	public void setLegalPersonIdNo(String legalPersonIdNo) {
		this.legalPersonIdNo = legalPersonIdNo;
	}

	public String getLegalPersonTel() {
		return legalPersonTel;
	}

	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}

	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}

	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}

	public String getLegalPersonAddr() {
		return legalPersonAddr;
	}

	public void setLegalPersonAddr(String legalPersonAddr) {
		this.legalPersonAddr = legalPersonAddr;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getRegAreaCode() {
		return regAreaCode;
	}

	public void setRegAreaCode(String regAreaCode) {
		this.regAreaCode = regAreaCode;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getBussLicenseNo() {
		return bussLicenseNo;
	}

	public void setBussLicenseNo(String bussLicenseNo) {
		this.bussLicenseNo = bussLicenseNo;
	}

	public String getBussAddr() {
		return bussAddr;
	}

	public void setBussAddr(String bussAddr) {
		this.bussAddr = bussAddr;
	}

	public String getBussAreaCode() {
		return bussAreaCode;
	}

	public void setBussAreaCode(String bussAreaCode) {
		this.bussAreaCode = bussAreaCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransDepartApprovalNo() {
		return transDepartApprovalNo;
	}

	public void setTransDepartApprovalNo(String transDepartApprovalNo) {
		this.transDepartApprovalNo = transDepartApprovalNo;
	}

	public String getTransBussLicenseNo() {
		return transBussLicenseNo;
	}

	public void setTransBussLicenseNo(String transBussLicenseNo) {
		this.transBussLicenseNo = transBussLicenseNo;
	}

	public Date getTransBussLicenseValidDate() {
		return transBussLicenseValidDate;
	}

	public void setTransBussLicenseValidDate(Date transBussLicenseValidDate) {
		this.transBussLicenseValidDate = transBussLicenseValidDate;
	}

	public String getTransBussScope() {
		return transBussScope;
	}

	public void setTransBussScope(String transBussScope) {
		this.transBussScope = transBussScope;
	}

	public Integer getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(Integer employeesNum) {
		this.employeesNum = employeesNum;
	}

	public Integer getDriverNum() {
		return driverNum;
	}

	public void setDriverNum(Integer driverNum) {
		this.driverNum = driverNum;
	}

	public Integer getUnlicensedDriverNum() {
		return unlicensedDriverNum;
	}

	public void setUnlicensedDriverNum(Integer unlicensedDriverNum) {
		this.unlicensedDriverNum = unlicensedDriverNum;
	}

	public Integer getLicensedDriverNum() {
		return licensedDriverNum;
	}

	public void setLicensedDriverNum(Integer licensedDriverNum) {
		this.licensedDriverNum = licensedDriverNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSimage() {
		return simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
	}

	public String getBimage() {
		return bimage;
	}

	public void setBimage(String bimage) {
		this.bimage = bimage;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	public String getAccidentOfYear() {
		return accidentOfYear;
	}

	public void setAccidentOfYear(String accidentOfYear) {
		this.accidentOfYear = accidentOfYear;
	}

	public String getComplaintsRemark() {
		return complaintsRemark;
	}

	public void setComplaintsRemark(String complaintsRemark) {
		this.complaintsRemark = complaintsRemark;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
    
    
	
    
}