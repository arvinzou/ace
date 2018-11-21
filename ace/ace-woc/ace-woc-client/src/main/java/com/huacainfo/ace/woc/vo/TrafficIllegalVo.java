
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.TrafficIllegal;

import java.math.BigDecimal;


public class TrafficIllegalVo extends TrafficIllegal {
	private static final long serialVersionUID = 1L;

	private String siteName;

	private String inspectTime;

    /**
     * 车牌号
     */
    private String plateNo;

    private String direction;

    private Integer axleCount;

    private BigDecimal totalMass;

    private BigDecimal overMass;

    private BigDecimal overRate;

    private BigDecimal speed;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(Integer axleCount) {
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
