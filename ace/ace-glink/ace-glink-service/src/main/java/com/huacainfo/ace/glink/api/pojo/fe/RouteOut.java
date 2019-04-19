package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName RouteOut
 * @Description 4G路由运行状态
 * @Author Arvin Zou
 * @Date 2019/4/18 9:51
 */
public class RouteOut extends BaseModel {

    private int RouteCount;//4g路由数量
    private List<RouteData> RouteData;//

    public int getRouteCount() {
        return RouteCount;
    }

    public void setRouteCount(int routeCount) {
        RouteCount = routeCount;
    }

    public List<RouteOut.RouteData> getRouteData() {
        return RouteData;
    }

    public void setRouteData(List<RouteOut.RouteData> routeData) {
        RouteData = routeData;
    }

    public static class RouteData {
        private int NodeID;//配电箱编号
        private int Status;//状态：1-在线，0-离线
        private String Signal;//信号强度，离线时为空
        private String UpdateTime;//状态更新时间

        public RouteData(int nodeID, int status, String signal, String updateTime) {
            super();
            NodeID = nodeID;
            Status = status;
            Signal = signal;
            UpdateTime = updateTime;
        }

        public RouteData() {
            super();
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

        public String getSignal() {
            return Signal;
        }

        public void setSignal(String signal) {
            Signal = signal;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            UpdateTime = updateTime;
        }
    }

}
