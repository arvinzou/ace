package com.huacainfo.ace.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TimestampKit
 * @Description 获取时间戳工具类
 * @Author Arvin
 * @Date 2019/3/18 10:16
 */
public class TimestampKit {
    /**
     * 默认的日期格式 .
     */
    public static String DATE_REGEX = "yyyy-MM-dd";
    /**
     * 默认的日期格式 .
     */
    public static String DATE_TIME_REGEX = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串判空
     *
     * @param str 字符串
     * @return boolean
     */
    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 获取时间戳(10位时间戳)，单位(秒)
     *
     * @return long
     */
    public static long getTimestamp10() {
        Date date = new Date();
        long time = date.getTime();
        String timeStr = String.valueOf(time);
        timeStr = timeStr.substring(0, timeStr.length() - 3);
        return Long.parseLong(timeStr);
    }

    /**
     * 获取时间戳(10位时间戳)，单位(秒)
     *
     * @return long
     */
    public static long getTimestamp10(Date date) {
        long time = date.getTime();
        String timeStr = String.valueOf(time);
        timeStr = timeStr.substring(0, timeStr.length() - 3);
        return Long.parseLong(timeStr);
    }

    /**
     * 获取时间戳(13位时间戳)，单位(毫秒)
     * 13位数的时间戳（毫秒）
     *
     * @return Long 13位数的时间戳（毫秒）
     */
    public static Long getTimestamp13() {
        Date date = new Date();
        long time = date.getTime();
        return time;
    }

    /**
     * 13位数的时间戳（毫秒）
     *
     * @param date 日期时间
     * @return Long 13位数的时间戳（毫秒）
     */
    public static Long getTimestamp13(Date date) {
        long time = date.getTime();
        return time;
    }


    /*
     * 将时间戳转换为时间    --  10位数的时间戳（秒）
     */
    public static String stamp10ToDate(long stamp10) {
        return stamp10ToDate(stamp10, DATE_TIME_REGEX);
    }

    /*
     * 将时间戳转换为时间    --  10位数的时间戳（秒）
     */
    public static String stamp10ToDate(long stamp10, String dateRegex) {
        dateRegex = isEmpty(dateRegex) ? DATE_TIME_REGEX : dateRegex;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateRegex);
        long timeLong = Long.valueOf(stamp10);

        return simpleDateFormat.format(new Date(timeLong * 1000L));
    }

    /*
     * 将时间戳转换为时间    --  13位数的时间戳（毫秒）
     */
    public static String stamp13ToDate(long stamp13) {
        return stamp13ToDate(stamp13, DATE_TIME_REGEX);
    }

    /*
     * 将时间戳转换为时间    --  13位数的时间戳（毫秒）
     */
    public static String stamp13ToDate(long stamp13, String dateRegex) {
        dateRegex = isEmpty(dateRegex) ? DATE_TIME_REGEX : dateRegex;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateRegex);
        long timeLong = Long.valueOf(stamp13);

        return simpleDateFormat.format(new Date(timeLong));
    }

}
