package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeTimerData;
import com.huacainfo.ace.glink.model.SeTimerMonth;

import java.util.List;


public class SeTimerDataVo extends SeTimerData {

    /**
     * 月列表
     */
    private List<SeTimerMonthVo> monthList;
    /**
     * 周列表
     */
    private List<SeTimerWeekVo> weekList;
    /**
     * 日列表
     */
    private List<SeTimerDayVo> dayList;


    public List<SeTimerMonthVo> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<SeTimerMonthVo> monthList) {
        this.monthList = monthList;
    }

    public List<SeTimerWeekVo> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<SeTimerWeekVo> weekList) {
        this.weekList = weekList;
    }

    public List<SeTimerDayVo> getDayList() {
        return dayList;
    }

    public void setDayList(List<SeTimerDayVo> dayList) {
        this.dayList = dayList;
    }
}
