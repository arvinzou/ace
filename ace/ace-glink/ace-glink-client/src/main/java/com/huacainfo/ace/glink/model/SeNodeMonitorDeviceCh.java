package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeNodeMonitorDeviceCh extends BaseModel {
    private String id;

    private String chName;

    private String deviceCode;

    private Integer status;

    private String cHReportTime;

    private String va;

    private String vb;

    private String vc;

    private String ia;

    private String ib;

    private String ic;

    private String pa;

    private String pb;

    private String pc;

    private String remark;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName == null ? null : chName.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getcHReportTime() {
        return cHReportTime;
    }

    public void setcHReportTime(String cHReportTime) {
        this.cHReportTime = cHReportTime == null ? null : cHReportTime.trim();
    }

    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va == null ? null : va.trim();
    }

    public String getVb() {
        return vb;
    }

    public void setVb(String vb) {
        this.vb = vb == null ? null : vb.trim();
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc == null ? null : vc.trim();
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia == null ? null : ia.trim();
    }

    public String getIb() {
        return ib;
    }

    public void setIb(String ib) {
        this.ib = ib == null ? null : ib.trim();
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic == null ? null : ic.trim();
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa == null ? null : pa.trim();
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb == null ? null : pb.trim();
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc == null ? null : pc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
