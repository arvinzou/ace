package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * @ClassName AttRecordExcel
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/22 15:27
 */
public class AttRecordExcel extends BaseModel {
    //"姓名", "身份类型", "数据来源", "打卡时间"
    private String name;
    private String userType;
    private String srcType;
    private Date attTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType;
    }

    public Date getAttTime() {
        return attTime;
    }

    public void setAttTime(Date attTime) {
        this.attTime = attTime;
    }
}
