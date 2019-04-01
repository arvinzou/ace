package com.huacainfo.ace.taa.constant;

/**
 * @ClassName CommConstant
 * @Description 通用常量
 * @Author Arvin Zou
 * @Date 2019/4/1 14:39
 */
public interface CommConstant {
    String DATE_REGEX_YMDHMS = "yyyy年M月d日HH时mm分ss秒";
    String DATE_REGEX_YMD = "yyyy年M月d日";
    String DATE_REGEX_YM = "yyyy年M月";

    //系统编码
    String SYS_ID = "taa";
    //短信签名
    String SMS_SIGN = "【常德市路长制信息管理平台】";

    /**
     * 短信通知模板编码
     */
    String SMS_MONTH_REPORT = "SMS-MONTH-REPORT";
    String SMS_REAL_TIME = "SMS-REAL-TIME";
}
