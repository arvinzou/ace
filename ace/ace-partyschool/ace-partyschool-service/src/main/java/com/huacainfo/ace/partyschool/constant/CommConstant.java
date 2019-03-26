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


}
