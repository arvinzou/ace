package com.huacainfo.ace.common.model;

import java.math.BigDecimal;

public class WxUser implements java.io.Serializable {
	private static final long serialVersionUID = 1302490874428907510L;
	private String unionId;

	private String openId;

	private String nickName;

	private String gender;

	private String city;

	private String province;

	private String country;

	private String avatarUrl;

	private String mobile;

	private String addr;

	private String email;

	private String name;
	private String role;

	private String areaCode;

	private String category;

	private String party;

	private BigDecimal latitude;

	private BigDecimal longitude;

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId == null ? null : unionId.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
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

	@Override
	public String toString() {
		return "WxUser{" +
				"unionId='" + unionId + '\'' +
				", openId='" + openId + '\'' +
				", nickName='" + nickName + '\'' +
				", gender='" + gender + '\'' +
				", city='" + city + '\'' +
				", province='" + province + '\'' +
				", country='" + country + '\'' +
				", avatarUrl='" + avatarUrl + '\'' +
				", mobile='" + mobile + '\'' +
				", addr='" + addr + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
}