package com.huacainfo.ace.glink.api.pojo.le;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName ChangeIn
 * @Description 关键变化数据上传 -入参列表
 * @Author Arvin Zou
 * @Date 2019/4/15 17:29
 */
public class ChangeIn extends BaseModel {
    private String area;//分区编号
    private List<NodeInfo> infos;//灯光建筑物数组集合

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<NodeInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<NodeInfo> infos) {
        this.infos = infos;
    }

    //=====================================
    public static class NodeInfo extends BaseModel {
        private String num;//建筑物编号
        private int type;//变化类型(type为1:亮灯变化 为2：分控器变化 为3:当前播放节目变化)
        private int isOnline;//（type为1）;//建筑物是否正常亮灯(1:正常 2:故障)
        private String program;//(type为3)当前播放节目
        private List<Point> points;//(type为2) 分控器数组，获取分控器状态信息

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public List<Point> getPoints() {
            return points;
        }

        public void setPoints(List<Point> points) {
            this.points = points;
        }
    }

    public static class Point extends BaseModel {
        /**
         * 分控器编号
         */
        private String pointNum;//（type为2）
        /**
         * 分控器状态信息(1:在线 2:离线 )
         */
        private int pointStatus;//（type为2）

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
