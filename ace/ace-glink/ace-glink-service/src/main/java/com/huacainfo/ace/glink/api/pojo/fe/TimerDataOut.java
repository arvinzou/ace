package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName TimerDataOut
 * @Description 系统定时任务数据
 * @Author Arvin Zou
 * @Date 2019/4/17 15:33
 */
public class TimerDataOut extends BaseModel {
    private int TimerCount;//定时任务数量
    private List<TimerData> TimerData;//定时任务数据集合

    public int getTimerCount() {
        return TimerCount;
    }

    public void setTimerCount(int timerCount) {
        TimerCount = timerCount;
    }

    public List<TimerDataOut.TimerData> getTimerData() {
        return TimerData;
    }

    public void setTimerData(List<TimerDataOut.TimerData> timerData) {
        TimerData = timerData;
    }

    /**
     * static class
     */
    public static class TimerData extends BaseModel {
        private int TimerID;//定时计划序号
        private String TimerName;//定时计划名称
        private int TimerEnable;//是否有效，1-有效；0-无效
        private String StartTime;//启动时间
        private int TaskNo;//调度任务号
        private MonthEnable MonthEnable;//月份数据集合
        private DayEnable DayEnable;//日期数据集合
        private WeekEnable WeekEnable;//星期数据集合

        public int getTimerID() {
            return TimerID;
        }

        public void setTimerID(int timerID) {
            TimerID = timerID;
        }

        public String getTimerName() {
            return TimerName;
        }

        public void setTimerName(String timerName) {
            TimerName = timerName;
        }

        public int getTimerEnable() {
            return TimerEnable;
        }

        public void setTimerEnable(int timerEnable) {
            TimerEnable = timerEnable;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String startTime) {
            StartTime = startTime;
        }

        public int getTaskNo() {
            return TaskNo;
        }

        public void setTaskNo(int taskNo) {
            TaskNo = taskNo;
        }

        public TimerDataOut.MonthEnable getMonthEnable() {
            return MonthEnable;
        }

        public void setMonthEnable(TimerDataOut.MonthEnable monthEnable) {
            MonthEnable = monthEnable;
        }

        public TimerDataOut.DayEnable getDayEnable() {
            return DayEnable;
        }

        public void setDayEnable(TimerDataOut.DayEnable dayEnable) {
            DayEnable = dayEnable;
        }

        public TimerDataOut.WeekEnable getWeekEnable() {
            return WeekEnable;
        }

        public void setWeekEnable(TimerDataOut.WeekEnable weekEnable) {
            WeekEnable = weekEnable;
        }
    }

    public static class MonthEnable extends BaseModel {
        private int M1;//是否有效，1-有效；0-无效 ;
        private int M2;//同上
        private int M3;
        private int M4;
        private int M5;
        private int M6;
        private int M7;
        private int M8;
        private int M9;
        private int M10;
        private int M11;
        private int M12;

        public int getM1() {
            return M1;
        }

        public void setM1(int m1) {
            M1 = m1;
        }

        public int getM2() {
            return M2;
        }

        public void setM2(int m2) {
            M2 = m2;
        }

        public int getM3() {
            return M3;
        }

        public void setM3(int m3) {
            M3 = m3;
        }

        public int getM4() {
            return M4;
        }

        public void setM4(int m4) {
            M4 = m4;
        }

        public int getM5() {
            return M5;
        }

        public void setM5(int m5) {
            M5 = m5;
        }

        public int getM6() {
            return M6;
        }

        public void setM6(int m6) {
            M6 = m6;
        }

        public int getM7() {
            return M7;
        }

        public void setM7(int m7) {
            M7 = m7;
        }

        public int getM8() {
            return M8;
        }

        public void setM8(int m8) {
            M8 = m8;
        }

        public int getM9() {
            return M9;
        }

        public void setM9(int m9) {
            M9 = m9;
        }

        public int getM10() {
            return M10;
        }

        public void setM10(int m10) {
            M10 = m10;
        }

        public int getM11() {
            return M11;
        }

        public void setM11(int m11) {
            M11 = m11;
        }

        public int getM12() {
            return M12;
        }

        public void setM12(int m12) {
            M12 = m12;
        }
    }

    public static class DayEnable extends BaseModel {
        private int D1;//是否有效，1-有效；0-无效 ;
        private int D2;//同上
        private int D3;
        private int D4;
        private int D5;
        private int D6;
        private int D7;
        private int D8;
        private int D9;
        private int D10;
        private int D11;
        private int D12;
        private int D13;
        private int D14;
        private int D15;
        private int D16;
        private int D17;
        private int D18;
        private int D19;
        private int D20;
        private int D21;
        private int D22;
        private int D23;
        private int D24;
        private int D25;
        private int D26;
        private int D27;
        private int D28;
        private int D29;
        private int D30;
        private int D31;

        public int getD1() {
            return D1;
        }

        public void setD1(int d1) {
            D1 = d1;
        }

        public int getD2() {
            return D2;
        }

        public void setD2(int d2) {
            D2 = d2;
        }

        public int getD3() {
            return D3;
        }

        public void setD3(int d3) {
            D3 = d3;
        }

        public int getD4() {
            return D4;
        }

        public void setD4(int d4) {
            D4 = d4;
        }

        public int getD5() {
            return D5;
        }

        public void setD5(int d5) {
            D5 = d5;
        }

        public int getD6() {
            return D6;
        }

        public void setD6(int d6) {
            D6 = d6;
        }

        public int getD7() {
            return D7;
        }

        public void setD7(int d7) {
            D7 = d7;
        }

        public int getD8() {
            return D8;
        }

        public void setD8(int d8) {
            D8 = d8;
        }

        public int getD9() {
            return D9;
        }

        public void setD9(int d9) {
            D9 = d9;
        }

        public int getD10() {
            return D10;
        }

        public void setD10(int d10) {
            D10 = d10;
        }

        public int getD11() {
            return D11;
        }

        public void setD11(int d11) {
            D11 = d11;
        }

        public int getD12() {
            return D12;
        }

        public void setD12(int d12) {
            D12 = d12;
        }

        public int getD13() {
            return D13;
        }

        public void setD13(int d13) {
            D13 = d13;
        }

        public int getD14() {
            return D14;
        }

        public void setD14(int d14) {
            D14 = d14;
        }

        public int getD15() {
            return D15;
        }

        public void setD15(int d15) {
            D15 = d15;
        }

        public int getD16() {
            return D16;
        }

        public void setD16(int d16) {
            D16 = d16;
        }

        public int getD17() {
            return D17;
        }

        public void setD17(int d17) {
            D17 = d17;
        }

        public int getD18() {
            return D18;
        }

        public void setD18(int d18) {
            D18 = d18;
        }

        public int getD19() {
            return D19;
        }

        public void setD19(int d19) {
            D19 = d19;
        }

        public int getD20() {
            return D20;
        }

        public void setD20(int d20) {
            D20 = d20;
        }

        public int getD21() {
            return D21;
        }

        public void setD21(int d21) {
            D21 = d21;
        }

        public int getD22() {
            return D22;
        }

        public void setD22(int d22) {
            D22 = d22;
        }

        public int getD23() {
            return D23;
        }

        public void setD23(int d23) {
            D23 = d23;
        }

        public int getD24() {
            return D24;
        }

        public void setD24(int d24) {
            D24 = d24;
        }

        public int getD25() {
            return D25;
        }

        public void setD25(int d25) {
            D25 = d25;
        }

        public int getD26() {
            return D26;
        }

        public void setD26(int d26) {
            D26 = d26;
        }

        public int getD27() {
            return D27;
        }

        public void setD27(int d27) {
            D27 = d27;
        }

        public int getD28() {
            return D28;
        }

        public void setD28(int d28) {
            D28 = d28;
        }

        public int getD29() {
            return D29;
        }

        public void setD29(int d29) {
            D29 = d29;
        }

        public int getD30() {
            return D30;
        }

        public void setD30(int d30) {
            D30 = d30;
        }

        public int getD31() {
            return D31;
        }

        public void setD31(int d31) {
            D31 = d31;
        }
    }

    public static class WeekEnable extends BaseModel {
        private int W1;//是否有效，1-有效；0-无效 ;
        private int W2;//同上
        private int W3;
        private int W4;
        private int W5;
        private int W6;
        private int W7;

        public int getW1() {
            return W1;
        }

        public void setW1(int w1) {
            W1 = w1;
        }

        public int getW2() {
            return W2;
        }

        public void setW2(int w2) {
            W2 = w2;
        }

        public int getW3() {
            return W3;
        }

        public void setW3(int w3) {
            W3 = w3;
        }

        public int getW4() {
            return W4;
        }

        public void setW4(int w4) {
            W4 = w4;
        }

        public int getW5() {
            return W5;
        }

        public void setW5(int w5) {
            W5 = w5;
        }

        public int getW6() {
            return W6;
        }

        public void setW6(int w6) {
            W6 = w6;
        }

        public int getW7() {
            return W7;
        }

        public void setW7(int w7) {
            W7 = w7;
        }
    }
}
