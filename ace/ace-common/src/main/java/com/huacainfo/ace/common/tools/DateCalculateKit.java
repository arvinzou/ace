package com.huacainfo.ace.common.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateCalculateKit
 * @Description 时间计算工具
 * @Author Arvin Zou
 * @Date 2019/6/11 16:15
 */
public class DateCalculateKit {
    /**
     * 单例模式
     */
    private DateCalculateKit() {
    }

    private static Calendar initCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal;
    }

    private static String toStr(Date date) {
        return DateUtil.toStr(date, DateUtil.DEFAULT_DATE_TIME_REGEX);
    }

    /**
     * 获得指定日期其当周的周一 0 点时间 -- Date格式
     *
     * @return Date
     */
    public static Date getWeekMorningDate(Date date) {
        Calendar cal = initCalendar(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得指定日期其当周的周日 24 点时间 -- Date格式
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getWeekNightDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekMorningDate(date));
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    /**
     * 获得指定日期其当周的周一 0 点时间 -- String字符串格式
     *
     * @param date 指定日期
     * @return String
     */
    public static String getWeekMorningStr(Date date) {
        return toStr(getWeekMorningDate(date));
    }

    /**
     * 获得指定日期其当周的周日 24 点时间 -- String字符串格式
     *
     * @param date 指定日期
     * @return String
     */
    public static String getWeekNightStr(Date date) {
        return toStr(getWeekNightDate(date));
    }


    /***
     * 获得指定月份的第一天 0 点时间 -- Date格式
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getMonthStartDate(Date date) {
        Calendar cal = initCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获得指定月份的最后一天 24 点时间 -- Date格式
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getMonthEndDate(Date date) {
        Calendar cal = initCalendar(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    /***
     * 获得指定月份的第一天 0 点时间 -- String字符串格式
     *
     * @param date 指定日期
     * @return String
     */
    public static String getMonthStartStr(Date date) {
        return toStr(getMonthStartDate(date));
    }

    /***
     * 获得指定月份的最后一天 24 点时间 -- String字符串格式
     * @param date 指定日期
     * @return String
     */
    public static String getMonthEndStr(Date date) {
        return toStr(getMonthEndDate(date));
    }

    /**
     * 获取指定日期对应季度的开始时间 -- Date格式
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getQuarterStartDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            time = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            System.out.println("日期格式转换错误");
        }
        return time;
    }

    /**
     * 获取指定日期对应季度的结束时间 -- Date格式
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getQuarterEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getQuarterStartDate(date));
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }

    /**
     * 获取指定日期对应季度的开始时间 -- String字符串格式
     *
     * @param date 指定日期
     * @return String
     */
    public static String getQuarterStartStr(Date date) {
        return toStr(getQuarterStartDate(date));
    }

    /**
     * 获取指定日期对应季度的结束时间 -- String字符串格式
     *
     * @param date 指定日期
     * @return String
     */
    public static String getQuarterEndStr(Date date) {
        return toStr(getQuarterEndDate(date));
    }
}
