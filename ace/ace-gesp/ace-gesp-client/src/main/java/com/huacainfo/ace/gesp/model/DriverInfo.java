package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class DriverInfo implements java.io.Serializable{
      
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String sex;

    private String idCard;

    private String status;

    private String deptId;

    private Date birthdate;

    private Date initDrivingLicDate;

    private String certNumber;

    private String certPersonType;

    private String certType;

    private String filingCertType;

    private String driverLicNo;

    private String driverLicCarType;

    private Date entryTime;

    private String certCompanyName;

    private String cooperationMode;

    private String tel;

    private String recordTime;

    private Date createDate;

    private String createUserId;

    private String createUserName;

    private Date lastModifyDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getInitDrivingLicDate() {
        return initDrivingLicDate;
    }

    public void setInitDrivingLicDate(Date initDrivingLicDate) {
        this.initDrivingLicDate = initDrivingLicDate;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber == null ? null : certNumber.trim();
    }

    public String getCertPersonType() {
        return certPersonType;
    }

    public void setCertPersonType(String certPersonType) {
        this.certPersonType = certPersonType == null ? null : certPersonType.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getFilingCertType() {
        return filingCertType;
    }

    public void setFilingCertType(String filingCertType) {
        this.filingCertType = filingCertType == null ? null : filingCertType.trim();
    }

    public String getDriverLicNo() {
        return driverLicNo;
    }

    public void setDriverLicNo(String driverLicNo) {
        this.driverLicNo = driverLicNo == null ? null : driverLicNo.trim();
    }

    public String getDriverLicCarType() {
        return driverLicCarType;
    }

    public void setDriverLicCarType(String driverLicCarType) {
        this.driverLicCarType = driverLicCarType == null ? null : driverLicCarType.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getCertCompanyName() {
        return certCompanyName;
    }

    public void setCertCompanyName(String certCompanyName) {
        this.certCompanyName = certCompanyName == null ? null : certCompanyName.trim();
    }

    public String getCooperationMode() {
        return cooperationMode;
    }

    public void setCooperationMode(String cooperationMode) {
        this.cooperationMode = cooperationMode == null ? null : cooperationMode.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime == null ? null : recordTime.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}