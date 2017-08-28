package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class Department implements java.io.Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    private String contactPost;

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
    
    private String businessClassify;
    
    private String regPro;
    
    private String regCity;
    
    private String bussPro;
    
    private String bussCity;

    private String fax;

    private String email;

    private String telphone;
    
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
    
    private String account;
    
    private String password;
    
    /**
     * 工商登记号
     */
    private String bussNum;
    private String orgCode;
    private String assetTotal;
    private String netAsset;
    private String yearSale;
    private String yearShall;
    private String ownerVehicle;
    private String vehicleNum;
    private String departType;
    private String ownStorageArea;
    private String storageArea;
    private String intnetNum;
    private String companyIp;
    private String ruote;
    private String scopeBuss;
    private String mainPeo;
    private String mainPost;
    private String mainPhone;
    private String mainEmail;
    private String mainTel;
    
    
    
    
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccidentOfYear() {
        return accidentOfYear;
    }

    public void setAccidentOfYear(String accidentOfYear) {
        this.accidentOfYear = accidentOfYear == null ? null : accidentOfYear.trim();
    }

    public String getComplaintsRemark() {
        return complaintsRemark;
    }

    public void setComplaintsRemark(String complaintsRemark) {
        this.complaintsRemark = complaintsRemark == null ? null : complaintsRemark.trim();
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId == null ? null : parentDepartmentId.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getContactQq() {
        return contactQq;
    }

    public void setContactQq(String contactQq) {
        this.contactQq = contactQq == null ? null : contactQq.trim();
    }

    public String getContactPost() {
		return contactPost;
	}

	public void setContactPost(String contactPost) {
		this.contactPost = contactPost;
	}

	public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax == null ? null : contactFax.trim();
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getLegalPersonIdType() {
        return legalPersonIdType;
    }

    public void setLegalPersonIdType(String legalPersonIdType) {
        this.legalPersonIdType = legalPersonIdType == null ? null : legalPersonIdType.trim();
    }

    public String getLegalPersonIdNo() {
        return legalPersonIdNo;
    }

    public void setLegalPersonIdNo(String legalPersonIdNo) {
        this.legalPersonIdNo = legalPersonIdNo == null ? null : legalPersonIdNo.trim();
    }

    public String getLegalPersonTel() {
        return legalPersonTel;
    }

    public void setLegalPersonTel(String legalPersonTel) {
        this.legalPersonTel = legalPersonTel == null ? null : legalPersonTel.trim();
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile == null ? null : legalPersonMobile.trim();
    }

    public String getLegalPersonAddr() {
        return legalPersonAddr;
    }

    public void setLegalPersonAddr(String legalPersonAddr) {
        this.legalPersonAddr = legalPersonAddr == null ? null : legalPersonAddr.trim();
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
        this.regCapital = regCapital == null ? null : regCapital.trim();
    }

    public String getRegAreaCode() {
        return regAreaCode;
    }

    public void setRegAreaCode(String regAreaCode) {
        this.regAreaCode = regAreaCode == null ? null : regAreaCode.trim();
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr == null ? null : regAddr.trim();
    }

    public String getRegPro() {
		return regPro;
	}

	public void setRegPro(String regPro) {
		this.regPro = regPro;
	}

	public String getRegCity() {
		return regCity;
	}

	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}

	public String getBussPro() {
		return bussPro;
	}

	public void setBussPro(String bussPro) {
		this.bussPro = bussPro;
	}

	public String getBussCity() {
		return bussCity;
	}

	public void setBussCity(String bussCity) {
		this.bussCity = bussCity;
	}

	public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public String getBussLicenseNo() {
        return bussLicenseNo;
    }

    public void setBussLicenseNo(String bussLicenseNo) {
        this.bussLicenseNo = bussLicenseNo == null ? null : bussLicenseNo.trim();
    }

    public String getBussAddr() {
        return bussAddr;
    }

    public void setBussAddr(String bussAddr) {
        this.bussAddr = bussAddr == null ? null : bussAddr.trim();
    }

    public String getBussAreaCode() {
        return bussAreaCode;
    }

    public void setBussAreaCode(String bussAreaCode) {
        this.bussAreaCode = bussAreaCode == null ? null : bussAreaCode.trim();
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

    public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getTransDepartApprovalNo() {
        return transDepartApprovalNo;
    }

    public void setTransDepartApprovalNo(String transDepartApprovalNo) {
        this.transDepartApprovalNo = transDepartApprovalNo == null ? null : transDepartApprovalNo.trim();
    }

    public String getTransBussLicenseNo() {
        return transBussLicenseNo;
    }

    public void setTransBussLicenseNo(String transBussLicenseNo) {
        this.transBussLicenseNo = transBussLicenseNo == null ? null : transBussLicenseNo.trim();
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
        this.transBussScope = transBussScope == null ? null : transBussScope.trim();
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
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage == null ? null : simage.trim();
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage == null ? null : bimage.trim();
    }

    public String getBusinessClassify() {
		return businessClassify;
	}

	public void setBusinessClassify(String businessClassify) {
		this.businessClassify = businessClassify;
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId
				+ ", parentDepartmentId=" + parentDepartmentId
				+ ", businessClassify=" + businessClassify
				+ ", departmentName=" + departmentName + ", shortName="
				+ shortName + ", category=" + category + ", areaCode="
				+ areaCode + ", pcode=" + pcode + ", contactName="
				+ contactName + ", contactTel=" + contactTel
				+ ", contactMobile=" + contactMobile + ", contactQq="
				+ contactQq + ", contactEmail=" + contactEmail
				+ ", contactFax=" + contactFax + ",contactPost="+contactPost+", legalPersonName="
				+ legalPersonName + ", legalPersonIdType=" + legalPersonIdType
				+ ", legalPersonIdNo=" + legalPersonIdNo + ", legalPersonTel="
				+ legalPersonTel + ", legalPersonMobile=" + legalPersonMobile
				+ ", legalPersonAddr=" + legalPersonAddr + ", regDate="
				+ regDate + ", regCapital=" + regCapital + ", regAreaCode="
				+ regAreaCode + ", regAddr=" + regAddr + ", nature=" + nature
				+ ", bussLicenseNo=" + bussLicenseNo + ", bussAddr=" + bussAddr
				+ ", bussAreaCode=" + bussAreaCode + ", fax=" + fax
				+ ", email=" + email + ", transDepartApprovalNo="
				+ transDepartApprovalNo + ", transBussLicenseNo="
				+ transBussLicenseNo + ", transBussLicenseValidDate="
				+ transBussLicenseValidDate + ", transBussScope="
				+ transBussScope + ", employeesNum=" + employeesNum
				+ ", driverNum=" + driverNum + ", unlicensedDriverNum="
				+ unlicensedDriverNum + ", licensedDriverNum="
				+ licensedDriverNum + ", status=" + status + ", type=" + type
				+ ", simage=" + simage + ", bimage=" + bimage
				+ ", createUserId=" + createUserId + ", createUserName="
				+ createUserName + ", createTime=" + createTime
				+ ", lastModifyTime=" + lastModifyTime + ", lastModifyUserId="
				+ lastModifyUserId + ", lastModifyUserName="
				+ lastModifyUserName + ", accidentOfYear=" + accidentOfYear
				+ ", complaintsRemark=" + complaintsRemark + ",bussNum="+bussNum+",orgCode="+
				orgCode+",assetTotal="+assetTotal+",netAsset="+netAsset+",yearSale="+
				yearSale+",yearShall="+yearShall+",ownerVehicle="+ownerVehicle+",vehicleNum="+
				vehicleNum+",departType="+departType+",ownStorageArea="+ownStorageArea+",storageArea="+
				storageArea+",intnetNum="+intnetNum+", companyIp="+companyIp+", ruote="+ruote+",scopeBuss="+
				scopeBuss+",mainPeo="+mainPeo+",mainPost="+mainPost+", mainPhone="+mainPhone+", mainEmail="+
				mainEmail+", mainTel="+mainTel+"]";
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getBussNum() {
		return bussNum;
	}

	public void setBussNum(String bussNum) {
		this.bussNum = bussNum;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAssetTotal() {
		return assetTotal;
	}

	public void setAssetTotal(String assetTotal) {
		this.assetTotal = assetTotal;
	}

	public String getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(String netAsset) {
		this.netAsset = netAsset;
	}

	public String getYearSale() {
		return yearSale;
	}

	public void setYearSale(String yearSale) {
		this.yearSale = yearSale;
	}

	public String getYearShall() {
		return yearShall;
	}

	public void setYearShall(String yearShall) {
		this.yearShall = yearShall;
	}

	public String getOwnerVehicle() {
		return ownerVehicle;
	}

	public void setOwnerVehicle(String ownerVehicle) {
		this.ownerVehicle = ownerVehicle;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public String getDepartType() {
		return departType;
	}

	public void setDepartType(String departType) {
		this.departType = departType;
	}

	public String getOwnStorageArea() {
		return ownStorageArea;
	}

	public void setOwnStorageArea(String ownStorageArea) {
		this.ownStorageArea = ownStorageArea;
	}

	public String getStorageArea() {
		return storageArea;
	}

	public void setStorageArea(String storageArea) {
		this.storageArea = storageArea;
	}

	public String getIntnetNum() {
		return intnetNum;
	}

	public void setIntnetNum(String intnetNum) {
		this.intnetNum = intnetNum;
	}

	public String getCompanyIp() {
		return companyIp;
	}

	public void setCompanyIp(String companyIp) {
		this.companyIp = companyIp;
	}

	public String getRuote() {
		return ruote;
	}

	public void setRuote(String ruote) {
		this.ruote = ruote;
	}

	public String getScopeBuss() {
		return scopeBuss;
	}

	public void setScopeBuss(String scopeBuss) {
		this.scopeBuss = scopeBuss;
	}

	public String getMainPeo() {
		return mainPeo;
	}

	public void setMainPeo(String mainPeo) {
		this.mainPeo = mainPeo;
	}

	public String getMainPost() {
		return mainPost;
	}

	public void setMainPost(String mainPost) {
		this.mainPost = mainPost;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public String getMainEmail() {
		return mainEmail;
	}

	public void setMainEmail(String mainEmail) {
		this.mainEmail = mainEmail;
	}

	public String getMainTel() {
		return mainTel;
	}

	public void setMainTel(String mainTel) {
		this.mainTel = mainTel;
	}
    
}