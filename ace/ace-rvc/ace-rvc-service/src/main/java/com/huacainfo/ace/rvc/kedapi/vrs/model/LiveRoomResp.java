package com.huacainfo.ace.rvc.kedapi.vrs.model;

import com.huacainfo.ace.rvc.kedapi.common.base.BaseModel;

import java.util.List;

/**
 * Created by Arvin on 2017/11/29.
 */
public class LiveRoomResp extends BaseModel {

    /**
     * 直播室的数量
     */
    private int roomcount;
    /**
     * 返回的页码
     */
    private int pageid;

    /**
     * 直播室信息
     */
    private List<RoomState> roomstate;

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public List<RoomState> getRoomstate() {
        return roomstate;
    }

    public void setRoomstate(List<RoomState> roomstate) {
        this.roomstate = roomstate;
    }


}





