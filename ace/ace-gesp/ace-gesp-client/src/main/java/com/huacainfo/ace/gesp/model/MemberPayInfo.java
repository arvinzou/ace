package com.huacainfo.ace.gesp.model;

import java.util.Date;

public class MemberPayInfo implements java.io.Serializable{  
	    
	private static final long serialVersionUID = 1L;

	private String id;

    private String memberCode;

    private String chargingItemId;
    
    private String chargingItemName;

    private String memberLevelId;

    private Date payDate;

    private Date endDate;

    private Long payAmount;

    private String status;

    private String remark;
    
    private String auditUserName;

    private String auditUserId;

    private Date auditDate;

    private String auditRemark;

    private Date createDate;

    private String createUserId;

    private String createUserName;

    private Date lastModifyDate;

    private String lastModifyUserId;

    private String lastModifyUserName;
    
    private String orginMemberLevel;
    private Date orginEndDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    public String getChargingItemId() {
        return chargingItemId;
    }

    public void setChargingItemId(String chargingItemId) {
        this.chargingItemId = chargingItemId == null ? null : chargingItemId.trim();
    }

    public String getChargingItemName() {
		return chargingItemName;
	}

	public void setChargingItemName(String chargingItemName) {
		this.chargingItemName = chargingItemName;
	}

	public String getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId == null ? null : memberLevelId.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName == null ? null : auditUserName.trim();
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
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

	public String getOrginMemberLevel() {
		return orginMemberLevel;
	}

	public void setOrginMemberLevel(String orginMemberLevel) {
		this.orginMemberLevel = orginMemberLevel;
	}

	public Date getOrginEndDate() {
		return orginEndDate;
	}

	public void setOrginEndDate(Date orginEndDate) {
		this.orginEndDate = orginEndDate;
	}
    
}