package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

import java.util.List;

/**
 * @ClassName GetBulidingDetailOut
 * @Description 武汉建筑物信息详情接口
 * @Author Arvin Zou
 * @Date 2019/4/15 18:29
 */
public class GetBulidingDetailOut extends LeBaseOut {

    private List<BulidingDetail> data;

    public List<BulidingDetail> getData() {
        return data;
    }

    public void setData(List<BulidingDetail> data) {
        this.data = data;
    }
    //**************************static classes**************************************

    public static class BulidingDetail {

        /**
         * 建筑物编号
         */
        private String BuildingNo;
        /**
         * 建筑物名称，
         */
        private String BuildingName;
        /**
         * 建筑物地址
         */
        private String BuildingAddress;
        /**
         * 1：在线，0：离线,
         */
        private String Status;
        /**
         * 故障设备数量（前一天）
         */
        private String BrokenLampCount;
        /**
         * 控制器数量,
         */
        private String ControllerCount;
        /**
         * 设备总数
         */
        private String LampCount;
        /**
         * 正在播放的节目,
         */
        private String IsPlayingStrategy;
        /**
         * 1：正在播放，0：未播放，
         */
        private String IsPlaying;
        /**
         * 建筑物经度
         */
        private String BuildingX;
        /**
         * 建筑物纬度
         */
        private String BuildingY;

        public String getBuildingNo() {
            return BuildingNo;
        }

        public void setBuildingNo(String buildingNo) {
            BuildingNo = buildingNo;
        }

        public String getBuildingName() {
            return BuildingName;
        }

        public void setBuildingName(String buildingName) {
            BuildingName = buildingName;
        }

        public String getBuildingAddress() {
            return BuildingAddress;
        }

        public void setBuildingAddress(String buildingAddress) {
            BuildingAddress = buildingAddress;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getBrokenLampCount() {
            return BrokenLampCount;
        }

        public void setBrokenLampCount(String brokenLampCount) {
            BrokenLampCount = brokenLampCount;
        }

        public String getControllerCount() {
            return ControllerCount;
        }

        public void setControllerCount(String controllerCount) {
            ControllerCount = controllerCount;
        }

        public String getLampCount() {
            return LampCount;
        }

        public void setLampCount(String lampCount) {
            LampCount = lampCount;
        }

        public String getIsPlayingStrategy() {
            return IsPlayingStrategy;
        }

        public void setIsPlayingStrategy(String isPlayingStrategy) {
            IsPlayingStrategy = isPlayingStrategy;
        }

        public String getIsPlaying() {
            return IsPlaying;
        }

        public void setIsPlaying(String isPlaying) {
            IsPlaying = isPlaying;
        }

        public String getBuildingX() {
            return BuildingX;
        }

        public void setBuildingX(String buildingX) {
            BuildingX = buildingX;
        }

        public String getBuildingY() {
            return BuildingY;
        }

        public void setBuildingY(String buildingY) {
            BuildingY = buildingY;
        }
    }
}
