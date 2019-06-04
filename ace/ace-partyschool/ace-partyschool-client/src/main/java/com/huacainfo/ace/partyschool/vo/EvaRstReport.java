package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * @ClassName EvaRstReport
 * @Description 测评报表对象
 * @Author Arvin Zou
 * @Date 2019/6/4 19:45
 */
public class EvaRstReport extends BaseModel {
    /**
     * 课程安排ID
     */
    private String classScheduleId;
    /**
     * 教职工姓名
     */
    private String teacherName;
    /**
     * 参评人数
     */
    private int testNum;
    /**
     * 去掉3个最高分和3个最低分后的最后总分
     */
    private int total;
    /**
     * 去掉3个最高分和3个最低分后的最后平均分
     */
    private BigDecimal avg;
    /**
     * 等次，综合得分(avg)：
     * 优秀-> 90=< avg;
     * 良好-> 80=< avg < 90;
     * 一般-> avg < 80;
     */
    private String rstLevel;
    /**
     * 名次
     */
    private int rank;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 概率：  很有必要
     */
    private String opt1;
    /**
     * 概率：  可以开设
     */
    private String opt2;
    /**
     * 概率：  可有可无
     */
    private String opt3;
    /**
     * 概率：  无需开设
     */
    private String opt4;

    public String getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(String classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTestNum() {
        return testNum;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public String getRstLevel() {
        return rstLevel;
    }

    public void setRstLevel(String rstLevel) {
        this.rstLevel = rstLevel;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }
}
