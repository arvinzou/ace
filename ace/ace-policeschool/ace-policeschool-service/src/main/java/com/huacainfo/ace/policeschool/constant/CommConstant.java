package com.huacainfo.ace.policeschool.constant;

/**
 * @Auther: Arvin
 * @Date: 2019/1/3 10:31
 * @Description:
 */
public interface CommConstant {
    /**
     * 短信签名
     */
    String SMS_SIGN_STR = "【常德市警官学校】";


    /**
     * 注册身份
     */
    String STUDENT = "student";//学员
    String TEACHER = "teacher";//教职工

    /**
     * 登录页面地址
     */
    String LOGIN_PAGE = "/policeschool/www/login/index.jsp?";


    /**
     * UNDO        -   未进行同步操作
     * USER_FAIL   -   员工信息同步失败
     * DEVICE_FAIL -   设备同步失败
     * DEVICE_OK   -   设备同步成功（全流程完）
     */
    String QY_SYN_STATE_UNDO = "UNDO";
    String QY_SYN_STATE_SYNC_FAIL = "SYNC_FAIL";
    String QY_SYN_STATE_SYNC_OK = "SYNC_OK";


}
