package com.huacainfo.ace.partyschool.constant;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 10:31
 * @Description:
 */
public interface CommConstant {
    // 系统标识
    String SYS_ID = "partyschool";
    //短信签名
    String SMS_SIGN_STR = "【中共常德市委党校】";

    /**
     * 注册身份
     */
    String STUDENT = "student";//学员
    String TEACHER = "teacher";//教职工

    /**
     * 考勤状态
     */
    String ATT_STATE_NO_SIGN = "NO_SIGN"; //"缺勤";
    String ATT_STATE_ON_TIME = "ON_TIME";//"正常";
    String ATT_STATE_BE_LATE = "BE_LATE";//"迟到";
    String ATT_STATE_LEAVE_EARLY = "LEAVE_EARLY";//"早退";

    String STU_ATT_SCOPE_AM_IN = "STU_AM_IN";
    String STU_ATT_SCOPE_AM_OUT = "STU_AM_OUT";
    String STU_ATT_SCOPE_PM_IN = "STU_PM_IN";
    String STU_ATT_SCOPE_PM_OUT = "STU_PM_OUT";
    String STU_ATT_SCOPE_NIGHT_IN = "STU_NIGHT_IN";

    String TEA_ATT_SCOPE_AM_IN = "TEA_AM_IN";
    String TEA_ATT_SCOPE_PM_OUT = "TEA_PM_OUT";

    String STU_ATT_AM_IN = "R_STU_AM_IN";
    String STU_ATT_AM_OUT = "R_STU_AM_OUT";
    String STU_ATT_PM_IN = "R_STU_PM_IN";
    String STU_ATT_PM_OUT = "R_STU_PM_OUT";
    String STU_ATT_NIGHT_IN = "R_STU_NIGHT_IN";

    String TEA_ATT_AM_IN = "R_TEA_AM_IN";
    String TEA_ATT_PM_OUT = "R_TEA_PM_OUT";
}
