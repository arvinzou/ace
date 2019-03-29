package com.huacainfo.ace.policeschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * @ClassName AttRecordExcel
 * @Description TODO
 * @Author
 * @Date 2019/3/26
 */
public class AttRecordExcel extends BaseModel {
    //"姓名",  "打卡时间"  "日期"
    private String userName;

    private String attenTime;

    private String attenDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getAttenDate() {
        return attenDate;
    }

    public void setAttenDate(String attenDate) {
        this.attenDate = attenDate;
    }


    public String getAttenTime() {
        return attenTime;
    }

    public void setAttenTime(String attenTime) {
        this.attenTime = attenTime;
    }
}
