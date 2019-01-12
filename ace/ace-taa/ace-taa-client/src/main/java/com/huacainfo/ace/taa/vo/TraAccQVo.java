package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.TraAcc;


public class TraAccQVo extends TraAcc {
    private static final long serialVersionUID = 1L;

    private String roadManName;

    private String startNum;

    private String endNum;


    private Integer startDate;

    private Integer endDate;

    private String keyword;

    /*     报表查询条件     */
    /**
     * 道路级别  select * from portal.dict t where t.category_id='170'
     */
    private String category;
    /**
     * 道路
     */
    private String roadId;
    /**
     * 死亡人数区间
     */
    private int upDeathNum;//死亡区间开始
    private int downDeathNum;//死亡区间结束
    /**
     * 受伤人数区间
     */
    private int upInjuriesNum;//受伤人数区间开始
    private int downInjuriesNum;//受伤人数区间结束

    public int getUpDeathNum() {
        return upDeathNum;
    }

    public void setUpDeathNum(int upDeathNum) {
        this.upDeathNum = upDeathNum;
    }

    public int getDownDeathNum() {
        return downDeathNum;
    }

    public void setDownDeathNum(int downDeathNum) {
        this.downDeathNum = downDeathNum;
    }

    public int getUpInjuriesNum() {
        return upInjuriesNum;
    }

    public void setUpInjuriesNum(int upInjuriesNum) {
        this.upInjuriesNum = upInjuriesNum;
    }

    public int getDownInjuriesNum() {
        return downInjuriesNum;
    }

    public void setDownInjuriesNum(int downInjuriesNum) {
        this.downInjuriesNum = downInjuriesNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getRoadManName() {
        return roadManName;
    }

    public void setRoadManName(String roadManName) {
        this.roadManName = roadManName;
    }

    public String getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
