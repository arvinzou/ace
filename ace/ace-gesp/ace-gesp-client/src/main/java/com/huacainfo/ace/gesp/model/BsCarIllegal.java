package com.huacainfo.ace.gesp.model;

import java.io.Serializable;

/**
 * 车辆违法信息类
 * 
 * @author yu
 *
 */
public class BsCarIllegal implements Serializable{

	private String id;     //编号
	private String plateNo;     //当前车牌号
	private String color;     //当前车牌颜色
	private String Organizationid;     //执法组织ID
	private String Organization;     //执法组织名称
	private String Code;     //编码
	private String NotiNo;     //粤执交违通号
	private String Party;     //物流企业
	private String Name;     //法律依据
	private String Amerce;     //罚款金额
	private String Time;     //违法时间
	private String Address;     //违法地点
	private String BriefOfCase;     //法律依据2
	private String Tache;     //案件归档类型
	private String Category2;     //车型
	private String OverHeight;     //超过高度
	private String OverLength;     //超过长度
	private String PuniNo;     //罚款单号
	private String InvoiceNo;     //发票号
	private String PunishmentContent;     //罚款内容
	private String IllegalLaws;     //法律条款
	private String PunishLaws;     //法律条款2
	private String Source;     //Source
	private String Status;     //Status
	private String Type;     //路政类型
	private String State;     //定数/目录
	private String PrveCaller;     //PrveCaller
	private String Address2;     //违规人地址
	private String Telephone;     //违规人手机
	private String Identity;     //违规人身份证
	private String BelongPlace;     //违规地点
	private String Model;     //型号
	private String FrameNumber;     //车架号
	private String EngineNumber;     //发动机号
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOrganizationid() {
		return Organizationid;
	}
	public void setOrganizationid(String organizationid) {
		Organizationid = organizationid;
	}
	public String getOrganization() {
		return Organization;
	}
	public void setOrganization(String organization) {
		Organization = organization;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getNotiNo() {
		return NotiNo;
	}
	public void setNotiNo(String notiNo) {
		NotiNo = notiNo;
	}
	public String getParty() {
		return Party;
	}
	public void setParty(String party) {
		Party = party;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAmerce() {
		return Amerce;
	}
	public void setAmerce(String amerce) {
		Amerce = amerce;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getBriefOfCase() {
		return BriefOfCase;
	}
	public void setBriefOfCase(String briefOfCase) {
		BriefOfCase = briefOfCase;
	}
	public String getTache() {
		return Tache;
	}
	public void setTache(String tache) {
		Tache = tache;
	}
	public String getCategory2() {
		return Category2;
	}
	public void setCategory2(String category2) {
		Category2 = category2;
	}
	public String getOverHeight() {
		return OverHeight;
	}
	public void setOverHeight(String overHeight) {
		OverHeight = overHeight;
	}
	public String getOverLength() {
		return OverLength;
	}
	public void setOverLength(String overLength) {
		OverLength = overLength;
	}
	public String getPuniNo() {
		return PuniNo;
	}
	public void setPuniNo(String puniNo) {
		PuniNo = puniNo;
	}
	public String getInvoiceNo() {
		return InvoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}
	public String getPunishmentContent() {
		return PunishmentContent;
	}
	public void setPunishmentContent(String punishmentContent) {
		PunishmentContent = punishmentContent;
	}
	public String getIllegalLaws() {
		return IllegalLaws;
	}
	public void setIllegalLaws(String illegalLaws) {
		IllegalLaws = illegalLaws;
	}
	public String getPunishLaws() {
		return PunishLaws;
	}
	public void setPunishLaws(String punishLaws) {
		PunishLaws = punishLaws;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPrveCaller() {
		return PrveCaller;
	}
	public void setPrveCaller(String prveCaller) {
		PrveCaller = prveCaller;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getIdentity() {
		return Identity;
	}
	public void setIdentity(String identity) {
		Identity = identity;
	}
	public String getBelongPlace() {
		return BelongPlace;
	}
	public void setBelongPlace(String belongPlace) {
		BelongPlace = belongPlace;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getFrameNumber() {
		return FrameNumber;
	}
	public void setFrameNumber(String frameNumber) {
		FrameNumber = frameNumber;
	}
	public String getEngineNumber() {
		return EngineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		EngineNumber = engineNumber;
	}
	
}
