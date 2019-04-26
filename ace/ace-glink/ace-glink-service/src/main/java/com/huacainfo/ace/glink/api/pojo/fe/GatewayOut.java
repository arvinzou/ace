package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName GatewayOut
 * @Description 网关运行状态
 * @Author Arvin Zou
 * @Date 2019/4/18 9:55
 */
public class GatewayOut extends BaseModel {

    public GatewayOut() {
    }

    private int GateCount;//网关设备数量
    private List<GatewayData> GateData;

    public GatewayOut(int gateCount, List<GatewayOut.GatewayData> gatewayData) {
        GateCount = gateCount;
        GateData = gatewayData;
    }

    public int getGateCount() {
        return GateCount;
    }

    public void setGateCount(int gateCount) {
        GateCount = gateCount;
    }

    public List<GatewayOut.GatewayData> getGateData() {
        return GateData;
    }

    public void setGateData(List<GatewayOut.GatewayData> gateData) {
        GateData = gateData;
    }

    public static class GatewayData {
        private int NodeID;//配电箱编号
        private int Status;//状态：1-在线，0-离线
        private String UpdateTime;//状态更新时间

        public GatewayData() {
        }

        public GatewayData(int nodeID, int status, String updateTime) {
            NodeID = nodeID;
            Status = status;
            UpdateTime = updateTime;
        }

        public int getNodeID() {
            return NodeID;
        }

        public void setNodeID(int nodeID) {
            NodeID = nodeID;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }
    }
}
