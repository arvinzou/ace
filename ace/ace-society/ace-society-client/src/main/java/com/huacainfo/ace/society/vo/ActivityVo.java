package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.model.ActivityDetail;

import java.util.List;


public class ActivityVo extends Activity {
private static final long serialVersionUID = 1L;

private List<ActivityDetailVo>  activityDetailVoList;

    public List<ActivityDetailVo> getActivityDetailVoList() {
        return activityDetailVoList;
    }

    public void setActivityDetailVoList(List<ActivityDetailVo> activityDetailVoList) {
        this.activityDetailVoList = activityDetailVoList;
    }

    private int total;

    private int signNum;

    private int amount;

    private String adStatus;

    private String arStatus;

    private String headimgurl;
    private String orgName;

    private Integer participant;

    private Integer host;

    public Integer getParticipant() {
        return participant;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public String getArStatus() {
        return arStatus;
    }

    public void setArStatus(String arStatus) {
        this.arStatus = arStatus;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }
}
