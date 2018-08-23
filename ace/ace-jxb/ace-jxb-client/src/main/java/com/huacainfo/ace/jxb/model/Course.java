package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 1-单课程 2-系列课程
     */
    private String type;
    /**
     * 内容类别
     */
    private String category;
    /**
     * 媒体类别
     */
    private String mediType;

    private String name;

    private String cover;

    private Integer duration;
    /**
     * 费用类型 0-免费 1-全部收费 2-会员免费
     */
    private String costType;
    /**
     * 费用 -- 销售价
     */
    private BigDecimal cost;
    /**
     * 课程原价
     */
    private BigDecimal primeCost;


    private Integer demandNum;
    /**
     * 点赞次数（起始人气）
     */
    private Integer likeNum;
    /**
     * 课程对象
     */
    private String objects;
    /**
     * 适合谁听
     */
    private String applicationObject;
    /**
     * 针对能力（课程主旨）
     */
    private String purport;
    /**
     * 在架状态 0 - 下架 1 - 上架
     */
    private String lineState;

    private String remark;

    private String status;

    private String createUserId;

    private Date createDate;

    private String introduce;

    /**
     * 单课程资源描述
     */
    private String mediaDesc;
    /**
     * 单课程资源文件地址
     */
    private String mediaUrl;

    public String getMediaDesc() {
        return mediaDesc;
    }

    public void setMediaDesc(String mediaDesc) {
        this.mediaDesc = mediaDesc;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getLineState() {
        return lineState;
    }

    public void setLineState(String lineState) {
        this.lineState = lineState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getMediType() {
        return mediType;
    }

    public void setMediType(String mediType) {
        this.mediType = mediType == null ? null : mediType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType == null ? null : costType.trim();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrimeCost() {
        return primeCost;
    }

    public void setPrimeCost(BigDecimal primeCost) {
        this.primeCost = primeCost;
    }

    public Integer getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(Integer demandNum) {
        this.demandNum = demandNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects == null ? null : objects.trim();
    }

    public String getApplicationObject() {
        return applicationObject;
    }

    public void setApplicationObject(String applicationObject) {
        this.applicationObject = applicationObject == null ? null : applicationObject.trim();
    }

    public String getPurport() {
        return purport;
    }

    public void setPurport(String purport) {
        this.purport = purport == null ? null : purport.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}