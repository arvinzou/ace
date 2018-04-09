
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Cases;

import java.math.BigDecimal;


public class CasesVo extends Cases {
    private static final long serialVersionUID = 1L;

    private String plateNo;

    /**
     * 当事人姓名
     */
    private String partyName;

    /**
     * 当事人公司
     */
    private String partyDeptName;

    /**
     * 轴数 -- 通行记录
     */
    private BigDecimal axleCount;
    /**
     * 总重量 -- 通行记录
     */
    private BigDecimal totalMass;
    /**
     * 超限 -- 通行记录
     */
    private BigDecimal overMass;

    /**
     * 超限率 -- 通行记录
     */
    private BigDecimal overRate;

    /**
     * 速度 -- 通行记录
     */
    private BigDecimal speed;

    private String chp1Name;
    private String chp2Name;

    /**
     * 关联车辆表 -- 信息
     */
    private String vehiclePlateNo;
    private String vehicleColor;
    private String vehicleType;
    private BigDecimal vehicleTotalMass;
    private BigDecimal vehicleApprovedMass;

    /**
     * 驾驶人信息
     */
    private String driverName;
    private String paperworkId;
    private String phoneNumber;
    private String address;
    private String certNumber;
    private String headImgUrl;

    public String getPaperworkId() {
        return paperworkId;
    }

    public void setPaperworkId(String paperworkId) {
        this.paperworkId = paperworkId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getVehiclePlateNo() {
        return vehiclePlateNo;
    }

    public void setVehiclePlateNo(String vehiclePlateNo) {
        this.vehiclePlateNo = vehiclePlateNo;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getVehicleTotalMass() {
        return vehicleTotalMass;
    }

    public void setVehicleTotalMass(BigDecimal vehicleTotalMass) {
        this.vehicleTotalMass = vehicleTotalMass;
    }

    public BigDecimal getVehicleApprovedMass() {
        return vehicleApprovedMass;
    }

    public void setVehicleApprovedMass(BigDecimal vehicleApprovedMass) {
        this.vehicleApprovedMass = vehicleApprovedMass;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getChp1Name() {
        return chp1Name;
    }

    public void setChp1Name(String chp1Name) {
        this.chp1Name = chp1Name;
    }

    public String getChp2Name() {
        return chp2Name;
    }

    public void setChp2Name(String chp2Name) {
        this.chp2Name = chp2Name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPartyDeptName() {
        return partyDeptName;
    }

    public void setPartyDeptName(String partyDeptName) {
        this.partyDeptName = partyDeptName;
    }

    public BigDecimal getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(BigDecimal axleCount) {
        this.axleCount = axleCount;
    }

    public BigDecimal getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(BigDecimal totalMass) {
        this.totalMass = totalMass;
    }

    public BigDecimal getOverMass() {
        return overMass;
    }

    public void setOverMass(BigDecimal overMass) {
        this.overMass = overMass;
    }

    public BigDecimal getOverRate() {
        return overRate;
    }

    public void setOverRate(BigDecimal overRate) {
        this.overRate = overRate;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }
}
