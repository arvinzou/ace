package com.huacainfo.ace.glink.api.pojo.light;

import com.huacainfo.ace.glink.api.pojo.base.BaseOut;

import java.util.List;

/**
 * @ClassName GetBulidingDetailOut
 * @Description 武汉建筑物信息详情接口
 * @Author Arvin Zou
 * @Date 2019/4/15 18:29
 */
public class GetBulidingDetailOut extends BaseOut {

    private List<BulidingDetail> data;

    public List<BulidingDetail> getData() {
        return data;
    }

    public void setData(List<BulidingDetail> data) {
        this.data = data;
    }
    //**************************static classes**************************************

    public static class BulidingDetail {
        private String MediaArea;//区域编号,
        private int Status;//": 1：在线，0：离线,
        private int BrokenLampCount;//": 故障设备数量,
        private int ControllerCount;//": 控制器数量,
        private int LampCount;//": 设备总数,
        private String IsPlayingStrategy;//": 正在播放的节目,
        private int IsPlaying;//": 1：正在播放，0：未播放

        public String getMediaArea() {
            return MediaArea;
        }

        public void setMediaArea(String mediaArea) {
            MediaArea = mediaArea;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public int getBrokenLampCount() {
            return BrokenLampCount;
        }

        public void setBrokenLampCount(int brokenLampCount) {
            BrokenLampCount = brokenLampCount;
        }

        public int getControllerCount() {
            return ControllerCount;
        }

        public void setControllerCount(int controllerCount) {
            ControllerCount = controllerCount;
        }

        public int getLampCount() {
            return LampCount;
        }

        public void setLampCount(int lampCount) {
            LampCount = lampCount;
        }

        public String getIsPlayingStrategy() {
            return IsPlayingStrategy;
        }

        public void setIsPlayingStrategy(String isPlayingStrategy) {
            IsPlayingStrategy = isPlayingStrategy;
        }

        public int getIsPlaying() {
            return IsPlaying;
        }

        public void setIsPlaying(int isPlaying) {
            IsPlaying = isPlaying;
        }
    }
}
