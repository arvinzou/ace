package com.huacainfo.ace.gis.model;

public class GpsChian implements java.io.Serializable{
    private String areaCode;

    private String areaName;

    private String gpsY;

    private String gpsX;

    private String pcode;

    private Integer level;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY == null ? null : gpsY.trim();
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX == null ? null : gpsX.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	@Override
	public String toString() {
		return "GpsChian [areaCode=" + areaCode + ", areaName=" + areaName
				+ ", gpsY=" + gpsY + ", gpsX=" + gpsX + ", pcode=" + pcode
				+ ", level=" + level + "]";
	}
    
}