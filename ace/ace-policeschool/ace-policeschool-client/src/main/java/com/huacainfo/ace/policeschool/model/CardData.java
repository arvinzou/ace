package com.huacainfo.ace.policeschool.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class CardData extends BaseModel {
    private String id;

    private String cardData;

    private String userId;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCardData() {
        return cardData;
    }

    public void setCardData(String cardData) {
        this.cardData = cardData == null ? null : cardData.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}