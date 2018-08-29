package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.CounselorPostLevel;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 15:31
 * @Description:
 */
public class CounselorPostLevelVo extends CounselorPostLevel {

    /**
     * 岗位名称
     */
    private String postName;
    /**
     * 咨询师名称
     */
    private String counselorName;

    /**
     * 分成比例
     */
    private BigDecimal ratio;

    private String checkYear;

    private String checkMonth;

    private String checkDay;

    private String checkQuarter;

    private Integer counselorNum;

    private BigDecimal turnover;

    public String getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(String checkYear) {
        this.checkYear = checkYear;
    }

    public String getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(String checkMonth) {
        this.checkMonth = checkMonth;
    }

    public String getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(String checkDay) {
        this.checkDay = checkDay;
    }

    public String getCheckQuarter() {
        return checkQuarter;
    }

    public void setCheckQuarter(String checkQuarter) {
        this.checkQuarter = checkQuarter;
    }

    public Integer getCounselorNum() {
        return counselorNum;
    }

    public void setCounselorNum(Integer counselorNum) {
        this.counselorNum = counselorNum;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
