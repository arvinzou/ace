package com.huacainfo.ace.portal.model;

import java.util.Date;

public class Users implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1302490874428907500L;
	/**用户编号*/
	private String userId;
	/**账户*/
	private String account;
	/**密码*/
	private String password;
	/**性别*/
	private String sex;
	/**身份证号*/
	private String idCard;
	/**用户名*/
	private String name;
	/**部门编号*/
	private String departmentId;
	/**所属地区编号*/
	private String areaCode;
	/**出生日期*/
	private Date birthday;
	/**状态（0停用正常1）*/
	private String stauts;
	/**最后登录时间*/
	private Date lastLoginTime;
	/**手机号*/
	private String mobile;
	/**电子邮箱*/
	private String email;
	/**座位*/
	private String seat;
	/**用户级别*/
	private String userLevel;
	/**创建时间*/
	private Date createTime;
	
	private String parentCorpId;
	
	private String corpName;
	
	/**电话号码*/
	private String telphone;
	/**传真*/
	private String fax;
	/**QQ*/
	private String qq;
	
	private String curSyid;

	private String id;

	private String formId;

	private String openId;
	
	
	public String getCurSyid() {
		return curSyid;
	}
	public void setCurSyid(String curSyid) {
		this.curSyid = curSyid;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public String getParentCorpId() {
		return parentCorpId;
	}
	public void setParentCorpId(String parentCorpId) {
		this.parentCorpId = parentCorpId;
	}
	
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}



	@Override
	public String toString() {
		return "Users{" +
				"userId='" + userId + '\'' +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", sex='" + sex + '\'' +
				", idCard='" + idCard + '\'' +
				", name='" + name + '\'' +
				", departmentId='" + departmentId + '\'' +
				", areaCode='" + areaCode + '\'' +
				", birthday=" + birthday +
				", stauts='" + stauts + '\'' +
				", lastLoginTime=" + lastLoginTime +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", seat='" + seat + '\'' +
				", userLevel='" + userLevel + '\'' +
				", createTime=" + createTime +
				", parentCorpId='" + parentCorpId + '\'' +
				", corpName='" + corpName + '\'' +
				", telphone='" + telphone + '\'' +
				", fax='" + fax + '\'' +
				", qq='" + qq + '\'' +
				", curSyid='" + curSyid + '\'' +
				", id='" + id + '\'' +
				", formId='" + formId + '\'' +
				", openId='" + openId + '\'' +
				'}';
	}
}