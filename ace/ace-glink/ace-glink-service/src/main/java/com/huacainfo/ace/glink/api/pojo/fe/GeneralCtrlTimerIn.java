package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName GeneralCtrlTimerIn
 * @Description 修改总控定时计划:   入参列表
 * @Author Arvin Zou
 * @Date 2019/4/17 16:43
 */
public class GeneralCtrlTimerIn extends BaseModel {

    private int TimerCount;//总控定时计划数量
    private List<TimerIn> TimerData;//定时计划数组集合

    public int getTimerCount() {
        return TimerCount;
    }

    public void setTimerCount(int timerCount) {
        TimerCount = timerCount;
    }

    public List<TimerIn> getTimerData() {
        return TimerData;
    }

    public void setTimerData(List<TimerIn> timerData) {
        TimerData = timerData;
    }

    /**
     * static class
     */
    public static class TimerIn extends BaseModel {
        private int TimerID;//定时计划序号 1-平时模式；2-节假日模式；3-重大节假日模式
        private String StartTime;//开始时间
        private String EndTime;//结束时间

        public TimerIn(int timerID, String startTime, String endTime) {
            TimerID = timerID;
            StartTime = startTime;
            EndTime = endTime;
        }

        public int getTimerID() {
            return TimerID;
        }

        public void setTimerID(int timerID) {
            TimerID = timerID;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String endTime) {
            EndTime = endTime;
        }
    }
}
