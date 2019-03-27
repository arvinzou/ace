package com.huacainfo.ace.partyschool.vo;

/**
 * @ClassName AttTeacherExcel
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/3/26 16:24
 */
public class AttTeacherExcel extends AttRecordExport {
    //"姓名", "身份类型", "打卡日期",
    //'上午签到', '下午签退' （教职工）

    private String name;
    private String userType;
    private String attDate;
    private String amIn;//上午签到   ps:教职工有此项
    private String pmOut;//下午签退 ps:教职工有此项

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

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getAmIn() {
        return amIn;
    }

    public void setAmIn(String amIn) {
        this.amIn = amIn;
    }

    public String getPmOut() {
        return pmOut;
    }

    public void setPmOut(String pmOut) {
        this.pmOut = pmOut;
    }
}
