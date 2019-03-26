package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName AttRecordExcel
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/22 15:27
 */
public class AttRecordExcel extends BaseModel {
    //"姓名", "身份类型", "打卡时间",'班次',
    // '上午签到', '上午签退','下午签到','下午签退','晚上签到'(学员)
    //'上午签到', '下午签退' （教职工）
    // "数据来源"

    private String name;
    private String userType;
    private String clsName;
    private String attDate;
    private String amIn;//上午签到   ps:教职工有此项
    private String amOut;//上午签退
    private String pmIn;//下午签到
    private String pmOut;//下午签退 ps:教职工有此项
    private String nightIn;//晚上签到

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getAmIn() {
        return amIn;
    }

    public void setAmIn(String amIn) {
        this.amIn = amIn;
    }

    public String getAmOut() {
        return amOut;
    }

    public void setAmOut(String amOut) {
        this.amOut = amOut;
    }

    public String getPmIn() {
        return pmIn;
    }

    public void setPmIn(String pmIn) {
        this.pmIn = pmIn;
    }

    public String getPmOut() {
        return pmOut;
    }

    public void setPmOut(String pmOut) {
        this.pmOut = pmOut;
    }

    public String getNightIn() {
        return nightIn;
    }

    public void setNightIn(String nightIn) {
        this.nightIn = nightIn;
    }

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

}
