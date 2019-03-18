package com.huacainfo.ace.common.plugins.qyplugin.pojo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName RecordLog
 * @Description 考勤日志
 * @Author Arvin Zou
 * @Date 2019/3/14 16:43
 */
public class RecordLog extends BaseModel {
    /**
     * 记录id
     */
    private String atten_id;
    /**
     * 设备序列号
     */
    private String atten_device;
    /**
     * 考勤编号
     */
    private String atten_uid;
    /**
     * 员工姓名
     */
    private String realname;
    /**
     * 部门名称
     */
    private String departname;
    /**
     * 打卡类型
     */
    private String atten_type;
    /**
     * 打卡时间戳
     */
    private String atten_time;
    /**
     * 打卡时间
     */
    private String atten_date;
    /**
     * 公司Id
     */
    private String companyId;
    /**
     * /设备名
     */
    private String remark;


    private String atten_status;


    public String getAtten_status() {
        return atten_status;
    }

    public void setAtten_status(String atten_status) {
        this.atten_status = atten_status;
    }

    public String getAtten_date() {
        return atten_date;
    }

    public void setAtten_date(String atten_date) {
        this.atten_date = atten_date;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAtten_type() {
        return atten_type;
    }

    public void setAtten_type(String atten_type) {
        this.atten_type = atten_type;
    }

    public String getAtten_uid() {
        return atten_uid;
    }

    public void setAtten_uid(String atten_uid) {
        this.atten_uid = atten_uid;
    }

    public String getAtten_id() {
        return atten_id;
    }

    public void setAtten_id(String atten_id) {
        this.atten_id = atten_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAtten_time() {
        return atten_time;
    }

    public void setAtten_time(String atten_time) {
        this.atten_time = atten_time;
    }

    public String getAtten_device() {
        return atten_device;
    }

    public void setAtten_device(String atten_device) {
        this.atten_device = atten_device;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
