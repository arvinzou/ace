package com.huacainfo.ace.glink.constant;

/**
 * @ClassName PortalKey
 * @Description 监控数据常用KEY
 * @Author Arvin Zou
 * @Date 2019/4/22 15:44
 */
public interface PortalKey {
    String offlineDeviceCount = "offlineDeviceCount";
    String offlineDeviceCount_ch = "离线设备总数";

    String moduleDeviceTotal = "moduleDeviceTotal";
    String moduleDeviceTotal_ch = "模块监控设备总数";

    String moduleOfflineDeviceTotal = "moduleOfflineDeviceTotal";
    String moduleOfflineDeviceTotal_ch = "模块监控设备离线数";

    String onlineDeviceCount = "onlineDeviceCount";
    String onlineDeviceCount_ch = "在线设备总数";

    String topBuildingCount = "topBuildingCount";
    String topBuildingCount_ch = "建筑物总数";

    String totalDeviceCount = "totalDeviceCount";
    String totalDeviceCount_ch = "设备总数";

    String totalErrLoopNum = "totalErrLoopNum";
    String totalErrLoopNum_ch = "故障回路总数";

    String totalIngStrategy = "totalIngStrategy";
    String totalIngStrategy_ch = "执行中策略数";

    String totalStrategy = "totalStrategy";
    String totalStrategy_ch = "总策略数";

    String totalScene = "totalScene";
    String totalScene_ch = "场景总数";

    String totalEnergy = "totalEnergy";
    String totalEnergy_ch = "累计能耗";


    /***
     * 弱电部分
     */
    //故障设备数
    String LE_BrokenLampCount = "SE-BrokenLampCount";
    String LE_BrokenLampCount_ch = "弱电-故障设备数";
    //总设备数
    String LE_LampCount = "SE-LampCount";
    String LE_LampCount_ch = "弱电-总设备数";
    //建筑物总数
    String LE_BuildingTotal = "LE-BuildingTotal";
    String LE_BuildingTotal_ch = "建筑物总数";
    //

    /***
     * 强电部分
     */
    String SE_RouterOffNum = "SE-RouterOffNum";
    String SE_RouterOffNum_ch = "路由器离线数";
    String SE_NodeTotal = "SE-NodeTotal";
    String SE_NodeTotal_ch = "总设备数(强电)";
    String SE_GatewayOffNum = "SE-GatewayOffNum";
    String SE_GatewayOffNum_ch = "网关离线数";
    String SE_NodeDeviceOffNum = "SE-NodeDeviceOffNum";
    String SE_NodeDeviceOffNum_ch = "模块离线数";
    String SE_NodeDeviceNum = "SE-NodeDeviceNum";
    String SE_NodeDeviceNum_ch = "模块总数";
    String SE_PowerTotal = "SE-PowerTotal";
    String SE_PowerTotal_ch = "总耗电量";
    String SE_UnitPrice = "SE-UnitPrice";
    String SE_UnitPrice_ch = "初始电费单价";
}
