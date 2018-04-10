
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Traffic;

import java.math.BigDecimal;
import java.util.List;


public class TrafficVo extends Traffic {
    private static final long serialVersionUID = 1L;

    private String siteName;

    private String monitorSiteName;

    private List<TrafficSubVo> trafficSubVo;

    /**
     * 关联车辆表 -- 信息
     */
    private String vehicleId;
    private String vehiclePlateNo;
    private String vehicleColor;
    private String vehicleType;
    private BigDecimal vehicleTotalMass;
    private BigDecimal vehicleApprovedMass;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public List<TrafficSubVo> getTrafficSubVo() {
        return trafficSubVo;
    }

    public void setTrafficSubVo(List<TrafficSubVo> trafficSubVo) {
        this.trafficSubVo = trafficSubVo;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMonitorSiteName() {
        return monitorSiteName;
    }

    public void setMonitorSiteName(String monitorSiteName) {
        this.monitorSiteName = monitorSiteName;
    }
}
