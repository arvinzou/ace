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
