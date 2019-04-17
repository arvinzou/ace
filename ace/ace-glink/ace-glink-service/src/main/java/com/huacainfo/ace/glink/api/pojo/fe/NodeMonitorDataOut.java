package com.huacainfo.ace.glink.api.pojo.fe;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.List;

/**
 * @ClassName NodeMonitorDataOut
 * @Description 配电箱监测数据
 * @Author Arvin Zou
 * @Date 2019/4/17 18:15
 */
public class NodeMonitorDataOut extends BaseModel {
    private int NodeCount;//配电箱节点数量
    private List<NodeMonitorData> NodeData;//监测数据数组

    public int getNodeCount() {
        return NodeCount;
    }

    public void setNodeCount(int nodeCount) {
        NodeCount = nodeCount;
    }

    public List<NodeMonitorData> getNodeData() {
        return NodeData;
    }

    public void setNodeData(List<NodeMonitorData> nodeData) {
        NodeData = nodeData;
    }

    /**
     * inner static class
     */
    public static class NodeMonitorData {

        private int NodeID;//配电箱编号
        private String ReportTime;//报告时间
        private int GateStatus;//网关状态：1-在线，0-离线
        private String GateReportTime;//网关状态报告时间
        private int RouteStatus;//路由器状态：1-在线，0-离线
        private String RouteSignal;//路由器信号值
        private String RouteReportTime;//路由器状态报告时间
        private int CurrentPreset;//当前场景
        private String PresetCaption;//当前场景描述
        private String PresetReportTime;//场景报告时间
        private String Temperature;//温度
        private String Humidity;//湿度
        private String WSDReportTime;//温湿度上报时间
        private String DoorStatus;//门状态（未知、关闭、打开）
        private String MeterID;//电表号
        private String MeterValue;//电表读数
        private String MeterReportTime;//电表上报时间
        private DeviceData DeviceData;//模块数据集合

        public int getNodeID() {
            return NodeID;
        }

        public void setNodeID(int nodeID) {
            NodeID = nodeID;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String reportTime) {
            ReportTime = reportTime;
        }

        public int getGateStatus() {
            return GateStatus;
        }

        public void setGateStatus(int gateStatus) {
            GateStatus = gateStatus;
        }

        public String getGateReportTime() {
            return GateReportTime;
        }

        public void setGateReportTime(String gateReportTime) {
            GateReportTime = gateReportTime;
        }

        public int getRouteStatus() {
            return RouteStatus;
        }

        public void setRouteStatus(int routeStatus) {
            RouteStatus = routeStatus;
        }

        public String getRouteSignal() {
            return RouteSignal;
        }

        public void setRouteSignal(String routeSignal) {
            RouteSignal = routeSignal;
        }

        public String getRouteReportTime() {
            return RouteReportTime;
        }

        public void setRouteReportTime(String routeReportTime) {
            RouteReportTime = routeReportTime;
        }

        public int getCurrentPreset() {
            return CurrentPreset;
        }

        public void setCurrentPreset(int currentPreset) {
            CurrentPreset = currentPreset;
        }

        public String getPresetCaption() {
            return PresetCaption;
        }

        public void setPresetCaption(String presetCaption) {
            PresetCaption = presetCaption;
        }

        public String getPresetReportTime() {
            return PresetReportTime;
        }

        public void setPresetReportTime(String presetReportTime) {
            PresetReportTime = presetReportTime;
        }

        public String getTemperature() {
            return Temperature;
        }

        public void setTemperature(String temperature) {
            Temperature = temperature;
        }

        public String getHumidity() {
            return Humidity;
        }

        public void setHumidity(String humidity) {
            Humidity = humidity;
        }

        public String getWSDReportTime() {
            return WSDReportTime;
        }

        public void setWSDReportTime(String WSDReportTime) {
            this.WSDReportTime = WSDReportTime;
        }

        public String getDoorStatus() {
            return DoorStatus;
        }

        public void setDoorStatus(String doorStatus) {
            DoorStatus = doorStatus;
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

        public String getMeterReportTime() {
            return MeterReportTime;
        }

        public void setMeterReportTime(String meterReportTime) {
            MeterReportTime = meterReportTime;
        }

        public NodeMonitorDataOut.DeviceData getDeviceData() {
            return DeviceData;
        }

        public void setDeviceData(NodeMonitorDataOut.DeviceData deviceData) {
            DeviceData = deviceData;
        }
    }

    public static class DeviceData {
        private String DeviceType;//模块类型
        private String DeviceCode;//模块代码
        private int DeviceBox;//模块地址
        private int DeviceStatus;//模块状态: 1-在线，O-离线
        private String DeviceReportTime;//模块报告时间

        private CHValue CH1Value;//回路状态对象
        private CHValue CH2Value;//
        private CHValue CH3Value;//
        private CHValue CH4Value;//
        private CHValue CH5Value;//
        private CHValue CH6Value;//
        private CHValue CH7Value;//
        private CHValue CH8Value;//
        private CHValue CH9Value;//
        private CHValue CH10Value;//
        private CHValue CH11Value;//
        private CHValue CH12Value;//

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

        public int getDeviceStatus() {
            return DeviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            DeviceStatus = deviceStatus;
        }

        public String getDeviceReportTime() {
            return DeviceReportTime;
        }

        public void setDeviceReportTime(String deviceReportTime) {
            DeviceReportTime = deviceReportTime;
        }

        public CHValue getCH1Value() {
            return CH1Value;
        }

        public void setCH1Value(CHValue CH1Value) {
            this.CH1Value = CH1Value;
        }

        public CHValue getCH2Value() {
            return CH2Value;
        }

        public void setCH2Value(CHValue CH2Value) {
            this.CH2Value = CH2Value;
        }

        public CHValue getCH3Value() {
            return CH3Value;
        }

        public void setCH3Value(CHValue CH3Value) {
            this.CH3Value = CH3Value;
        }

        public CHValue getCH4Value() {
            return CH4Value;
        }

        public void setCH4Value(CHValue CH4Value) {
            this.CH4Value = CH4Value;
        }

        public CHValue getCH5Value() {
            return CH5Value;
        }

        public void setCH5Value(CHValue CH5Value) {
            this.CH5Value = CH5Value;
        }

        public CHValue getCH6Value() {
            return CH6Value;
        }

        public void setCH6Value(CHValue CH6Value) {
            this.CH6Value = CH6Value;
        }

        public CHValue getCH7Value() {
            return CH7Value;
        }

        public void setCH7Value(CHValue CH7Value) {
            this.CH7Value = CH7Value;
        }

        public CHValue getCH8Value() {
            return CH8Value;
        }

        public void setCH8Value(CHValue CH8Value) {
            this.CH8Value = CH8Value;
        }

        public CHValue getCH9Value() {
            return CH9Value;
        }

        public void setCH9Value(CHValue CH9Value) {
            this.CH9Value = CH9Value;
        }

        public CHValue getCH10Value() {
            return CH10Value;
        }

        public void setCH10Value(CHValue CH10Value) {
            this.CH10Value = CH10Value;
        }

        public CHValue getCH11Value() {
            return CH11Value;
        }

        public void setCH11Value(CHValue CH11Value) {
            this.CH11Value = CH11Value;
        }

        public CHValue getCH12Value() {
            return CH12Value;
        }

        public void setCH12Value(CHValue CH12Value) {
            this.CH12Value = CH12Value;
        }
    }

    public static class CHValue {
        private int Status;//回路状态：  1-开、0-关、2-未知
        private String CHReportTime;//回路状态报告时间
        private String Va;//A 相电压
        private String Vb;//B 相电压
        private String Vc;//C 相电压
        private String Ia;//A 相电流
        private String Ib;//B 相电流
        private String Ic;//C 相电流
        private String Pa;//A 相视在功率
        private String Pb;//B 相视在功率
        private String Pc;//C 相视在功率

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public String getCHReportTime() {
            return CHReportTime;
        }

        public void setCHReportTime(String CHReportTime) {
            this.CHReportTime = CHReportTime;
        }

        public String getVa() {
            return Va;
        }

        public void setVa(String va) {
            Va = va;
        }

        public String getVb() {
            return Vb;
        }

        public void setVb(String vb) {
            Vb = vb;
        }

        public String getVc() {
            return Vc;
        }

        public void setVc(String vc) {
            Vc = vc;
        }

        public String getIa() {
            return Ia;
        }

        public void setIa(String ia) {
            Ia = ia;
        }

        public String getIb() {
            return Ib;
        }

        public void setIb(String ib) {
            Ib = ib;
        }

        public String getIc() {
            return Ic;
        }

        public void setIc(String ic) {
            Ic = ic;
        }

        public String getPa() {
            return Pa;
        }

        public void setPa(String pa) {
            Pa = pa;
        }

        public String getPb() {
            return Pb;
        }

        public void setPb(String pb) {
            Pb = pb;
        }

        public String getPc() {
            return Pc;
        }

        public void setPc(String pc) {
            Pc = pc;
        }
    }
}
