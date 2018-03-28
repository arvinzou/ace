
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Cases;

import java.math.BigDecimal;


public class CasesVo extends Cases {
	private static final long serialVersionUID = 1L;

    private String vehicleNo;

    private String partyName;

    /**
     * 当事人公司
     */
    private String partyDeptName;

    /**
     * 轴数
     */
    private BigDecimal axleCount;
    /**
     * 总重量
     */
    private BigDecimal totalMass;
    /**
     * 超限
     */
    private BigDecimal overMass;

    /**
     * 超限率
     */
    private BigDecimal overRate;

    /**
     * 速度
     */
    private BigDecimal speed;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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
