package com.huacainfo.ace.glink.api.pojo.light;

/**
 * @ClassName LightStrategyIn
 * @Description 灯光策略下发 --入参列表
 * @Author Arvin Zou
 * @Date 2019/4/15 15:19
 */
public class LightStrategyIn {
    //模式（日程模式：1 假日模式：2 事件模式:3）优先级依次上升
    public static final String MODE_SCHEDULE = "1";
    public static final String MODE_HOLIDAY = "2";
    public static final String MODE_EVENT = "3";
    private String area;//分区编号(2)
    private String pattern;//模式（日程模式：1 假日模式：2 事件模式:3）优先级依次上升
    private int isWeek;//(pattern为1) 按周执行（0否1是）
    private String weeks;//(pattern为1) 按周[1,2,3,4,5,6,7]分别表示周一至周日
    private int isMonth;//(pattern为1) 按月执行(0否1整月)
    private String months;//(pattern为1) 按月[1,2,3,4,5,6,7,8,9,10,11,12]分别表示整月执行
    private String startDate;//(pattern为2);假日模式：开始日期
    private String stopDate;//(pattern为2)假日模式：结束日期
    private String specialDate;//（pattern为3）事件模式：具体的特殊日期
    private String startTime;//策略的开始时间
    private String stopTime;//策略的结束时间
    private String strategy;//分区已编辑完毕的策略编号（或名称）

    private LightStrategyIn() {
    }

    public LightStrategyIn(String strategy, String area, String startTime, String stopTime,
                           int isWeek, String weeks, int isMonth, String months) {
        this.strategy = strategy;
        this.area = area;
        this.startTime = startTime;
        this.stopTime = stopTime;

        this.pattern = "1";
        //星期,月份，二选一
        this.isWeek = isWeek;
        this.weeks = weeks;
        this.isMonth = isMonth;
        this.months = months;
    }

    public LightStrategyIn(String strategy, String area, String startTime, String stopTime,
                           String pattern, String startDate, String stopDate) {
        this.strategy = strategy;
        this.area = area;
        this.startTime = startTime;
        this.stopTime = stopTime;

        this.pattern = "2";
        this.stopDate = stopDate;
        this.startDate = startDate;
    }

    public LightStrategyIn(String strategy, String area, String startTime, String stopTime, String specialDate) {
        this.strategy = strategy;
        this.area = area;
        this.startTime = startTime;
        this.stopTime = stopTime;

        this.pattern = "3";
        this.specialDate = specialDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getIsWeek() {
        return isWeek;
    }

    public void setIsWeek(int isWeek) {
        this.isWeek = isWeek;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public int getIsMonth() {
        return isMonth;
    }

    public void setIsMonth(int isMonth) {
        this.isMonth = isMonth;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getSpecialDate() {
        return specialDate;
    }

    public void setSpecialDate(String specialDate) {
        this.specialDate = specialDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
