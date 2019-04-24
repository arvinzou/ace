package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName MeterBoxOut
 * @Description 配电箱电表数据
 * @Author Arvin Zou
 * @Date 2019/4/17 14:42
 */
public class MeterBoxOut extends BaseModel {

    public MeterBoxOut() {
    }

    public MeterBoxOut(int nodeMeterCount, List<MeterData> nodeMeterData) {
        NodeMeterCount = nodeMeterCount;
        NodeMeterData = nodeMeterData;
    }

    private int NodeMeterCount;             //电表数量
    private List<MeterData> NodeMeterData;  //电表数据集合

    public int getNodeMeterCount() {
        return NodeMeterCount;
    }

    public void setNodeMeterCount(int nodeMeterCount) {
        NodeMeterCount = nodeMeterCount;
    }

    public List<MeterData> getNodeMeterData() {
        return NodeMeterData;
    }

    public void setNodeMeterData(List<MeterData> nodeMeterData) {
        NodeMeterData = nodeMeterData;
    }

    /**
     * static class
     */
    public static class MeterData extends BaseModel {

        private int NodeID;          //配电箱编号
        private String MeterID;         //电表表号
        private String MeterValue;      //电表当前读数
        private String UpdateTime;      //电表当前读数更新时间

        public int getNodeID() {
            return NodeID;
        }

        public void setNodeID(int nodeID) {
            NodeID = nodeID;
        }

        public String getMeterID() {
            return MeterID;
        }

        public void setMeterID(String meterID) {
            MeterID = meterID;
        }

        public String getMeterValue() {
            return MeterValue;
        }

        public void setMeterValue(String meterValue) {
            MeterValue = meterValue;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.UpdateTime = updateTime;
        }
    }

}
