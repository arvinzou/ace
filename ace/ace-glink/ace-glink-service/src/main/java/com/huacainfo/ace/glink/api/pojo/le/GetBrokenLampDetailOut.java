package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

import java.util.List;

/**
 * @ClassName GetBrokenLampDetailOut
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/15 18:19
 */
public class GetBrokenLampDetailOut extends LeBaseOut {

    private List<BrokenLamp> data;

    public List<BrokenLamp> getData() {
        return data;
    }

    public void setData(List<BrokenLamp> data) {
        this.data = data;
    }

    //**************************static classes**************************************

    public static class BrokenLamp {
        private String MediaArea;// 控制区域编号
        private String Controller;//控制器编号,
        private int ChannelNo;//通道编号,
        private int LampNo;//灯组编号

        public String getMediaArea() {
            return MediaArea;
        }

        public void setMediaArea(String mediaArea) {
            MediaArea = mediaArea;
        }

        public String getController() {
            return Controller;
        }

        public void setController(String controller) {
            Controller = controller;
        }

        public int getChannelNo() {
            return ChannelNo;
        }

        public void setChannelNo(int channelNo) {
            ChannelNo = channelNo;
        }

        public int getLampNo() {
            return LampNo;
        }

        public void setLampNo(int lampNo) {
            LampNo = lampNo;
        }
    }
}
