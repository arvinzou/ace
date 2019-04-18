package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName YearCron
 * @Description 总控全年排程
 * @Author Arvin Zou
 * @Date 2019/4/17 17:16
 */
public class YearCron extends BaseModel {
    /**
     * 月份排程，31个字符，字符内容为（1~3）：
     * 1、平日模式
     * 2、节日模式
     * 3、重大节假日模式
     * 不足31字符串的补足31个字符；
     */
    private String M1;//"1111111111111111111111111111111"
    private String M2;//"1111111111111111111111111111111"
    private String M3;//"1111111111111111111111111111111"
    private String M4;//"1111111111111111111111111111111"
    private String M5;//"1111111111111111111111111111111"
    private String M6;//"1111111111111111111111111111111"
    private String M7;//"1111111111111111111111111111111"
    private String M8;//"1111111111111111111111111111111"
    private String M9;//"1111111111111111111111111111111"
    private String M10;//"1111111111111111111111111111111"
    private String M11;//"1111111111111111111111111111111"
    private String M12;//"1111111111111111111111111111111"

    public String getM1() {
        return M1;
    }

    public void setM1(String m1) {
        M1 = m1;
    }

    public String getM2() {
        return M2;
    }

    public void setM2(String m2) {
        M2 = m2;
    }

    public String getM3() {
        return M3;
    }

    public void setM3(String m3) {
        M3 = m3;
    }

    public String getM4() {
        return M4;
    }

    public void setM4(String m4) {
        M4 = m4;
    }

    public String getM5() {
        return M5;
    }

    public void setM5(String m5) {
        M5 = m5;
    }

    public String getM6() {
        return M6;
    }

    public void setM6(String m6) {
        M6 = m6;
    }

    public String getM7() {
        return M7;
    }

    public void setM7(String m7) {
        M7 = m7;
    }

    public String getM8() {
        return M8;
    }

    public void setM8(String m8) {
        M8 = m8;
    }

    public String getM9() {
        return M9;
    }

    public void setM9(String m9) {
        M9 = m9;
    }

    public String getM10() {
        return M10;
    }

    public void setM10(String m10) {
        M10 = m10;
    }

    public String getM11() {
        return M11;
    }

    public void setM11(String m11) {
        M11 = m11;
    }

    public String getM12() {
        return M12;
    }

    public void setM12(String m12) {
        M12 = m12;
    }
}
