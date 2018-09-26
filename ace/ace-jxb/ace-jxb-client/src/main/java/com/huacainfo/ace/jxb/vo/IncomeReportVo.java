package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * @Auther: Arvin
 * @Date: 2018/9/26 09:40
 * @Description:
 */
public class IncomeReportVo extends BaseModel {
    /**
     * 咨询收入
     */
    private BigDecimal consultIncomeTotal;
    private BigDecimal consultIncomeTeacher;
    private BigDecimal consultIncomePlatform;
    /**
     * 课程收入
     */
    private BigDecimal courseIncomeTotal;
    private BigDecimal courseIncomeTeacher;
    private BigDecimal courseIncomePlatform;
    /**
     * 提成收入
     */
    private BigDecimal studioIncome;
    /**
     * 老师收入总计
     */
    private BigDecimal teacherTotal;
    /**
     * 公司收入总计
     */
    private BigDecimal platformTotal;
    /**
     * 基本信息
     */
    private String teacherName;
    private int devPeopleNum;
    private BigDecimal ratio;
    private String imagePhotoUrl;
    private String certification;

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getImagePhotoUrl() {
        return imagePhotoUrl;
    }

    public void setImagePhotoUrl(String imagePhotoUrl) {
        this.imagePhotoUrl = imagePhotoUrl;
    }

    public BigDecimal getConsultIncomeTotal() {
        return consultIncomeTotal;
    }

    public void setConsultIncomeTotal(BigDecimal consultIncomeTotal) {
        this.consultIncomeTotal = consultIncomeTotal;
    }

    public BigDecimal getConsultIncomeTeacher() {
        return consultIncomeTeacher;
    }

    public void setConsultIncomeTeacher(BigDecimal consultIncomeTeacher) {
        this.consultIncomeTeacher = consultIncomeTeacher;
    }

    public BigDecimal getConsultIncomePlatform() {
        return consultIncomePlatform;
    }

    public void setConsultIncomePlatform(BigDecimal consultIncomePlatform) {
        this.consultIncomePlatform = consultIncomePlatform;
    }

    public BigDecimal getCourseIncomeTotal() {
        return courseIncomeTotal;
    }

    public void setCourseIncomeTotal(BigDecimal courseIncomeTotal) {
        this.courseIncomeTotal = courseIncomeTotal;
    }

    public BigDecimal getCourseIncomeTeacher() {
        return courseIncomeTeacher;
    }

    public void setCourseIncomeTeacher(BigDecimal courseIncomeTeacher) {
        this.courseIncomeTeacher = courseIncomeTeacher;
    }

    public BigDecimal getCourseIncomePlatform() {
        return courseIncomePlatform;
    }

    public void setCourseIncomePlatform(BigDecimal courseIncomePlatform) {
        this.courseIncomePlatform = courseIncomePlatform;
    }

    public BigDecimal getStudioIncome() {
        return studioIncome;
    }

    public void setStudioIncome(BigDecimal studioIncome) {
        this.studioIncome = studioIncome;
    }

    public BigDecimal getTeacherTotal() {
        return teacherTotal;
    }

    public void setTeacherTotal(BigDecimal teacherTotal) {
        this.teacherTotal = teacherTotal;
    }

    public BigDecimal getPlatformTotal() {
        return platformTotal;
    }

    public void setPlatformTotal(BigDecimal platformTotal) {
        this.platformTotal = platformTotal;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getDevPeopleNum() {
        return devPeopleNum;
    }

    public void setDevPeopleNum(int devPeopleNum) {
        this.devPeopleNum = devPeopleNum;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
