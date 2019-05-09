package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.common.model.BaseModel;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;

import java.util.List;

/**
 * @ClassName StatsOut
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/15 17:16
 */
public class StatsOut extends LeBaseOut {

    private List<StatsObj> data;//返回数据

    public List<StatsObj> getData() {
        return data;
    }

    public void setData(List<StatsObj> data) {
        this.data = data;
    }

    //**************************static classes**************************************

    public static class StatsObj extends BaseModel {
        private String num;//建筑物/分区编号
        private int isOnline;//建筑物/分区是否正常亮灯(1:正常 2:故障)
        private List<NodeInfo> nodeInfos;//建筑物下灯光节点集合

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public List<NodeInfo> getNodeInfos() {
            return nodeInfos;
        }

        public void setNodeInfos(List<NodeInfo> nodeInfos) {
            this.nodeInfos = nodeInfos;
        }
    }

    public static class NodeInfo extends BaseModel {
        private String nodeNum;//节点编号
        private int isOnline;//灯光节点是否正常亮灯(1:正常 2:故障)
        private String routerNum;//路由器编号
        private int signal;//4G信号（或光纤）信号强度（按强度由弱到强分为1、2、3、4、5）
        private int flow;//累计消耗流量(MB)
        private String bbs;//路由器附着基站
        private String program;//当前播放节目
        private List<Points> points;//分控器数组，获取分控器状态信息

        public String getNodeNum() {
            return nodeNum;
        }

        public void setNodeNum(String nodeNum) {
            this.nodeNum = nodeNum;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public String getRouterNum() {
            return routerNum;
        }

        public void setRouterNum(String routerNum) {
            this.routerNum = routerNum;
        }

        public int getSignal() {
            return signal;
        }

        public void setSignal(int signal) {
            this.signal = signal;
        }

        public int getFlow() {
            return flow;
        }

        public void setFlow(int flow) {
            this.flow = flow;
        }

        public String getBbs() {
            return bbs;
        }

        public void setBbs(String bbs) {
            this.bbs = bbs;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public List<Points> getPoints() {
            return points;
        }

        public void setPoints(List<Points> points) {
            this.points = points;
        }
    }

    public static class Points extends BaseModel {

        private String pointNum;//分控器编号

        private int pointStatus;//分控器状态信息(1:在线 2:离线)


        public String getPointNum() {
            return pointNum;
        }

        public void setPointNum(String pointNum) {
            this.pointNum = pointNum;
        }

        public int getPointStatus() {
            return pointStatus;
        }

        public void setPointStatus(int pointStatus) {
            this.pointStatus = pointStatus;
        }
    }
}
