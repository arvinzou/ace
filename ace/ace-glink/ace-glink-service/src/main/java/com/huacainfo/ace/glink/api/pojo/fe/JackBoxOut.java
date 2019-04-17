package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName JackBoxOut
 * @Description 配电箱基础数据
 * @Author Arvin Zou
 * @Date 2019/4/17 10:33
 */
public class JackBoxOut extends BaseModel {

    private int NodeCount;//配电箱数量

    private List<JackBox> NodeGroup;//配电箱集合

    public int getNodeCount() {
        return NodeCount;
    }

    public void setNodeCount(int nodeCount) {
        NodeCount = nodeCount;
    }

    public List<JackBox> getNodeGroup() {
        return NodeGroup;
    }

    public void setNodeGroup(List<JackBox> nodeGroup) {
        NodeGroup = nodeGroup;
    }

    /**
     * static class
     */
    public static class JackBox extends BaseModel {
        private String NodeID;              //配电箱编号
        private String Local;               //位置标识
        private String IPAddress;           //网关IP地址
        private String RouteIPAddress;      //路由器IP地址
        private String AreaNodeID;          //区域id索引
        private String PX;                  //经度
        private String PY;                  //纬度
        private String MeterID;             //电表表号
        private String MeterType;           //电表品牌或类型
        private String Engineer;            //维护人员
        private String Tel;                 //维护人员电话
        private int DeviceCount;            //模块数量
        private List<Device> DeviceData;    //模块集合

        public String getNodeID() {
            return NodeID;
        }

        public void setNodeID(String nodeID) {
            NodeID = nodeID;
        }

        public String getLocal() {
            return Local;
        }

        public void setLocal(String local) {
            Local = local;
        }

        public String getIPAddress() {
            return IPAddress;
        }

        public void setIPAddress(String IPAddress) {
            this.IPAddress = IPAddress;
        }

        public String getRouteIPAddress() {
            return RouteIPAddress;
        }

        public void setRouteIPAddress(String routeIPAddress) {
            RouteIPAddress = routeIPAddress;
        }

        public String getAreaNodeID() {
            return AreaNodeID;
        }

        public void setAreaNodeID(String areaNodeID) {
            AreaNodeID = areaNodeID;
        }

        public String getPX() {
            return PX;
        }

        public void setPX(String PX) {
            this.PX = PX;
        }

        public String getPY() {
            return PY;
        }

        public void setPY(String PY) {
            this.PY = PY;
        }

        public String getMeterID() {
            return MeterID;
        }

        public void setMeterID(String meterID) {
            MeterID = meterID;
        }

        public String getMeterType() {
            return MeterType;
        }

        public void setMeterType(String meterType) {
            MeterType = meterType;
        }

        public String getEngineer() {
            return Engineer;
        }

        public void setEngineer(String engineer) {
            Engineer = engineer;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public int getDeviceCount() {
            return DeviceCount;
        }

        public void setDeviceCount(int deviceCount) {
            DeviceCount = deviceCount;
        }

        public List<Device> getDeviceData() {
            return DeviceData;
        }

        public void setDeviceData(List<Device> deviceData) {
            DeviceData = deviceData;
        }
    }

    public static class Device extends BaseModel {
        private String DeviceType;      //模块类型
        private String DeviceCode;      //模块代码
        private int DeviceBox;       //模块地址
        private String CH1Name;         //回路名称1
        private String CH2Name;         //回路名称2
        private String CH3Name;         //回路名称3
        private String CH4Name;         //回路名称4
        private String CH5Name;         //回路名称5
        private String CH6Name;         //回路名称6
        private String CH7Name;         //回路名称7
        private String CH8Name;         //回路名称8
        private String CH9Name;         //回路名称9
        private String CH10Name;        //回路名称10
        private String CH11Name;        //回路名称11
        private String CH12Name;        //回路名称12
        private String CH1Control;      //回路1 平日及节假日控制，如 0-1
        private String CH2Control;      //同上
        private String CH3Control;
        private String CH4Control;
        private String CH5Control;
        private String CH6Control;
        private String CH7Control;
        private String CH8Control;
        private String CH9Control;
        private String CH10Control;
        private String CH11Control;
        private String CH12Control;

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String deviceType) {
            DeviceType = deviceType;
        }

        public String getDeviceCode() {
            return DeviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            DeviceCode = deviceCode;
        }

        public int getDeviceBox() {
            return DeviceBox;
        }

        public void setDeviceBox(int deviceBox) {
            DeviceBox = deviceBox;
        }

        public String getCH1Name() {
            return CH1Name;
        }

        public void setCH1Name(String CH1Name) {
            this.CH1Name = CH1Name;
        }

        public String getCH2Name() {
            return CH2Name;
        }

        public void setCH2Name(String CH2Name) {
            this.CH2Name = CH2Name;
        }

        public String getCH3Name() {
            return CH3Name;
        }

        public void setCH3Name(String CH3Name) {
            this.CH3Name = CH3Name;
        }

        public String getCH4Name() {
            return CH4Name;
        }

        public void setCH4Name(String CH4Name) {
            this.CH4Name = CH4Name;
        }

        public String getCH5Name() {
            return CH5Name;
        }

        public void setCH5Name(String CH5Name) {
            this.CH5Name = CH5Name;
        }

        public String getCH6Name() {
            return CH6Name;
        }

        public void setCH6Name(String CH6Name) {
            this.CH6Name = CH6Name;
        }

        public String getCH7Name() {
            return CH7Name;
        }

        public void setCH7Name(String CH7Name) {
            this.CH7Name = CH7Name;
        }

        public String getCH8Name() {
            return CH8Name;
        }

        public void setCH8Name(String CH8Name) {
            this.CH8Name = CH8Name;
        }

        public String getCH9Name() {
            return CH9Name;
        }

        public void setCH9Name(String CH9Name) {
            this.CH9Name = CH9Name;
        }

        public String getCH10Name() {
            return CH10Name;
        }

        public void setCH10Name(String CH10Name) {
            this.CH10Name = CH10Name;
        }

        public String getCH11Name() {
            return CH11Name;
        }

        public void setCH11Name(String CH11Name) {
            this.CH11Name = CH11Name;
        }

        public String getCH12Name() {
            return CH12Name;
        }

        public void setCH12Name(String CH12Name) {
            this.CH12Name = CH12Name;
        }

        public String getCH1Control() {
            return CH1Control;
        }

        public void setCH1Control(String CH1Control) {
            this.CH1Control = CH1Control;
        }

        public String getCH2Control() {
            return CH2Control;
        }

        public void setCH2Control(String CH2Control) {
            this.CH2Control = CH2Control;
        }

        public String getCH3Control() {
            return CH3Control;
        }

        public void setCH3Control(String CH3Control) {
            this.CH3Control = CH3Control;
        }

        public String getCH4Control() {
            return CH4Control;
        }

        public void setCH4Control(String CH4Control) {
            this.CH4Control = CH4Control;
        }

        public String getCH5Control() {
            return CH5Control;
        }

        public void setCH5Control(String CH5Control) {
            this.CH5Control = CH5Control;
        }

        public String getCH6Control() {
            return CH6Control;
        }

        public void setCH6Control(String CH6Control) {
            this.CH6Control = CH6Control;
        }

        public String getCH7Control() {
            return CH7Control;
        }

        public void setCH7Control(String CH7Control) {
            this.CH7Control = CH7Control;
        }

        public String getCH8Control() {
            return CH8Control;
        }

        public void setCH8Control(String CH8Control) {
            this.CH8Control = CH8Control;
        }

        public String getCH9Control() {
            return CH9Control;
        }

        public void setCH9Control(String CH9Control) {
            this.CH9Control = CH9Control;
        }

        public String getCH10Control() {
            return CH10Control;
        }

        public void setCH10Control(String CH10Control) {
            this.CH10Control = CH10Control;
        }

        public String getCH11Control() {
            return CH11Control;
        }

        public void setCH11Control(String CH11Control) {
            this.CH11Control = CH11Control;
        }

        public String getCH12Control() {
            return CH12Control;
        }

        public void setCH12Control(String CH12Control) {
            this.CH12Control = CH12Control;
        }
    }
}
