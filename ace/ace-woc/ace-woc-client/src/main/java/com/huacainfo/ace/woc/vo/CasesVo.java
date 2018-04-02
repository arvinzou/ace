
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Cases;

import java.math.BigDecimal;


public class CasesVo extends Cases {
	private static final long serialVersionUID = 1L;

    private String plateNo;

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

    private String  driverName;
    private String  chp1Name;
    private String  chp2Name;

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
