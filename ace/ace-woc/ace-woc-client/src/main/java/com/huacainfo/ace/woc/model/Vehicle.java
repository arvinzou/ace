package com.huacainfo.ace.woc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HuaCai003
 */
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -7063674829801878096L;
    private String id;

    private String plateNo;

    private String vehicleColor;

    private String ownerId;

    private String ownerCompanyId;



    private Integer axleCount;

    private String vehicleType;

    private String model;

    private String engineNo;

    private String VIN;

    private Date registerDate;

    private Date issueDate;

    private String licenceIssuingAuthority;

    private BigDecimal totalMass;

    private BigDecimal unladedMass;

    private BigDecimal approvedMass;

    private String containerInsideSize;

    private BigDecimal tractionMass;

    private String remark;

    private String status;

    private String createUserId;

    private String createUserName;

    private Date createDate;

    private String lastModifyUserId;

    private String lastModifyUserName;

    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo == null ? null : plateNo.trim();
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor == null ? null : vehicleColor.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public void setOwnerCompanyId(String ownerCompanyId) {
        this.ownerCompanyId = ownerCompanyId == null ? null : ownerCompanyId.trim();
    }


    public Integer getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(Integer axleCount) {
        this.axleCount = axleCount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo == null ? null : engineNo.trim();
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN == null ? null : VIN.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getLicenceIssuingAuthority() {
        return licenceIssuingAuthority;
    }

    public void setLicenceIssuingAuthority(String licenceIssuingAuthority) {
        this.licenceIssuingAuthority = licenceIssuingAuthority == null ? null : licenceIssuingAuthority.trim();
    }

    public BigDecimal getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(BigDecimal totalMass) {
        this.totalMass = totalMass;
    }

    public BigDecimal getUnladedMass() {
        return unladedMass;
    }

    public void setUnladedMass(BigDecimal unladedMass) {
        this.unladedMass = unladedMass;
    }

    public BigDecimal getApprovedMass() {
        return approvedMass;
    }

    public void setApprovedMass(BigDecimal approvedMass) {
        this.approvedMass = approvedMass;
    }

    public String getContainerInsideSize() {
        return containerInsideSize;
    }

    public void setContainerInsideSize(String containerInsideSize) {
        this.containerInsideSize = containerInsideSize == null ? null : containerInsideSize.trim();
    }

    public BigDecimal getTractionMass() {
        return tractionMass;
    }

    public void setTractionMass(BigDecimal tractionMass) {
        this.tractionMass = tractionMass;
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