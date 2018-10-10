package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.model.OrderInfo;
import com.huacainfo.ace.society.model.SpaceOccupyInfo;

import java.util.List;


public class OrderInfoVo extends OrderInfo {
    private static final long serialVersionUID = 1L;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * 订单详情列表
     */
    private List<OrderDetail> detailList;

    /**
     * 场地预约情况
     */
    private SpaceOccupyInfo spaceOccupyInfo;

    public SpaceOccupyInfo getSpaceOccupyInfo() {
        return spaceOccupyInfo;
    }

    public void setSpaceOccupyInfo(SpaceOccupyInfo spaceOccupyInfo) {
        this.spaceOccupyInfo = spaceOccupyInfo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<OrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OrderDetail> detailList) {
        this.detailList = detailList;
    }
}
