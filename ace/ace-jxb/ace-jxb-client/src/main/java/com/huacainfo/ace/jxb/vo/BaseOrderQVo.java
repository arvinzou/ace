package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.jxb.model.BaseOrder;


public class BaseOrderQVo extends BaseOrder{
    private static final long serialVersionUID = 1L;

    /**
     * 多状态查询
     */
    private String payStatusArray;
    //dao.xml内查询使用
    private String[] statusArray;

    private String month;

    private String year;

    private String cpuTag;


    public String getCpuTag() {
        return cpuTag;
    }

    public void setCpuTag(String cpuTag) {
        this.cpuTag = cpuTag;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getStatusArray() {

        if (StringUtil.isNotEmpty(payStatusArray)) {
            return payStatusArray.split(",");
        }

        return statusArray;
    }

    public void setStatusArray(String[] statusArray) {

        this.statusArray = statusArray;
    }

    public String getPayStatusArray() {
        return payStatusArray;
    }

    public void setPayStatusArray(String payStatusArray) {
        this.payStatusArray = payStatusArray;
    }
}
