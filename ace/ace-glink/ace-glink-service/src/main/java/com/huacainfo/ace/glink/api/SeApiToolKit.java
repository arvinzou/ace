package com.huacainfo.ace.glink.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.URLKit;
import com.huacainfo.ace.glink.api.pojo.fe.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SeApiToolKit
 * @Description 强电接口--调用工具
 * @Author Arvin Zou
 * @Date 2019/4/15 9:57
 */
public class SeApiToolKit {

    static Logger logger = LoggerFactory.getLogger(SeApiToolKit.class);


    private static final String API_DOMAIN_DEV = PropertyUtil.getProperty("fe.api.url");

    private static final String APP_KEY = PropertyUtil.getProperty("fe.api.appkey");

    /**
     * 单例模式
     */
    private SeApiToolKit() {

    }

    private static Map<String, String> common(String interFaceType) {
        Map<String, String> map = new HashMap<>();
        map.clear();

        map.put("Appkey", APP_KEY);
        map.put("InterFaceType", interFaceType);

        return map;
    }

    private static String parse(Map<String, String> p) {
        return URLKit.mapToStr(p);
    }

    private static String apiURLStr(int interFaceType) {
        return API_DOMAIN_DEV + "InterFaceType=" + interFaceType + "&Appkey=" + APP_KEY;
    }

    private static String post(String url, String data) {
        logger.debug(" *********request info*********\n" +
                "request url: {}\n" +
                "request data: {}\n" +
                "*********request info end*********", url, data);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return HttpKit.post(url, data, headers);
    }


    /**
     * 3.1、获取项目区域数据（InterFaceType=1） -- 初始化一次，后期根据需求手动调用
     *
     * @return ProjectAreaOut
     */
    public static ProjectAreaOut getAreaProjectInfo() {
        String requestURL = apiURLStr(1);
        System.out.println(requestURL);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getAreaProjectInfo], response={}", rstJson);

        return JsonUtil.toObject(rstJson, ProjectAreaOut.class);
    }


    /**
     * 3.2、获取配电箱基础数据（InterFaceType=2）    -- 初始化一次，后期根据需求手动调用
     *
     * @return JackBoxOut
     */
    public static JackBoxOut getBaseNodeInfo() {

        String requestURL = apiURLStr(2);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getBaseNodeInfo], response={}", rstJson);

        return JsonUtil.toObject(rstJson, JackBoxOut.class);
    }

    //3.3、获取单个配电箱监测数据（InterFaceType=3） -- 页面手动及时调用
    public static Map<String, Object> getNodeMonitorSingleData(String nodeId) {

        String requestURL = apiURLStr(3);
        requestURL += "&NodeID=" + nodeId;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getNodeMonitorSingleData], response={}", rstJson);

        return JsonUtil.toMap(rstJson);

    }

    /**
     * 3.4、获取多个配电箱监测数据（InterFaceType=4） -- 页面手动及时调用
     *
     * @param nodeGroup 配电箱ID组合，用英文','隔开
     * @return Map<String, Object>
     */
    public static NodeMonitorDataOut getNodeMonitorListData(String nodeGroup) {

        String requestURL = apiURLStr(4);
        requestURL += "&NodeGroup=" + nodeGroup;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getNodeMonitorListData], response={}", rstJson);


        return JsonUtil.toObject(rstJson, NodeMonitorDataOut.class);
    }

    /**
     * 3.5、获取全部配电箱电表数据（InterFaceType=5）  -- 每天定时获取一次
     *
     * @return MeterBoxOut
     */
    public static MeterBoxOut getAllMeterData() {

        //type2
        String requestURL = apiURLStr(5);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getAllMeterData], response={}", rstJson);


        return JsonUtil.toObject(rstJson, MeterBoxOut.class);
    }

    /**
     * 3.6、获取4G 路由器运行状态（InterFaceType=6） -- 页面手动调用
     *
     * @return Map<String, Object>
     */
    public static RouteOut get4GRouterState() {

        //type2
        String requestURL = apiURLStr(6);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.get4GRouterState], response={}", rstJson);

        return JsonUtil.toObject(rstJson, RouteOut.class);
    }

    /**
     * 3.7、获取网关设备运行状态（InterFaceType=7）    -- 页面手动调用
     *
     * @return Map<String, Object>
     */
    public static GatewayOut getGatewayState() {

        String requestURL = apiURLStr(7);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getGatewayState], response={}", rstJson);


        return JsonUtil.toObject(rstJson, GatewayOut.class);
    }


    /**
     * 3.8、获取逻辑区定义数据（InterFaceType=8）    -- 初始化一次，后期根据需求手动调用
     *
     * @return CustomAreaOut
     */
    public static CustomAreaOut getCustomAreaInfo() {

        String requestURL = apiURLStr(8);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getCustomAreaInfo], response={}", rstJson);


        return JsonUtil.toObject(rstJson, CustomAreaOut.class);
    }

    /**
     * 3.9、获取区域任务数据（InterFaceType=9）  -- 页面手动调用
     *
     * @return AreaTaskOut
     */
    public static AreaTaskOut getAreaTaskInfo() {

        String requestURL = apiURLStr(9);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getAreaTaskInfo], response={}", rstJson);


        return JsonUtil.toObject(rstJson, AreaTaskOut.class);
    }

    /**
     * 3.10、获取场景定义列表（InterFaceType=10） -- 初始化一次，后期根据需求手动调用
     *
     * @return PresetDataOut
     */
    public static PresetDataOut getPresetData() {

        String requestURL = apiURLStr(10);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getPresetData], response={}", rstJson);


        return JsonUtil.toObject(rstJson, PresetDataOut.class);
    }

    /**
     * 3.11、获取系统定时任务数据（InterFaceType=11） -- 初始化一次，后期根据需求手动调用
     *
     * @return TimerDataOut
     */
    public static TimerDataOut getTimerData() {

        String requestURL = apiURLStr(11);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getTimerData], response={}", rstJson);

        return JsonUtil.toObject(rstJson, TimerDataOut.class);
    }

    /**
     * 3.12、执行区域场景指令（InterFaceType=12） -- 页面手动调用
     *
     * @param AreaNO     区号
     * @param PresetNo   场景编号
     * @param AreaNodeID 区域编号
     * @return Map<String, Object>
     */
    public static Map<String, Object> executePreset(String AreaNO, String PresetNo, String AreaNodeID) {

        String requestURL = apiURLStr(12);
        requestURL += "&AreaNO=" + AreaNO + "&PresetNo=" + PresetNo + "&AreaNodeID=" + AreaNodeID;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.executePreset], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.13、执行一键控制任务（InterFaceType=13）   --  页面手动调用
     *
     * @param TaskNO 任务序号
     * @return Map<String, Object>
     */
    public static Map<String, Object> executeTask(String TaskNO) {

        String requestURL = apiURLStr(13);
        requestURL += "&TaskNO=" + TaskNO;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.executeTask], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }

    //TODO 3.14、报警信息推送 -- 构建服务器，提供接收数据接口

    /**
     * 3.15、系统定时计划修改（InterFaceType=14）   --页面手动调用
     *
     * @param timerData 入参
     * @return Map<String, Object>
     */
    public static Map<String, Object> updateTimer(TimerDataOut.TimerData timerData) {

        String requestURL = apiURLStr(14);
        String rstJson = post(requestURL, timerData.toString());
        logger.debug(" [SeApiToolKit.updateTimer], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.16、获取逻辑区当前场景（InterFaceType=15）  --页面手动调用
     *
     * @param AreaNodeID 区域编号
     * @return Map<String, Object>
     */
    public static Map<String, Object> getCurrentPreset(String AreaNodeID) {

        String requestURL = apiURLStr(15);
        requestURL += "&AreaNodeID=" + AreaNodeID;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getCurrentPreset], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }

    /**
     * 3.17、查询总控定时计划（InterFaceType=17）   --页面手动调用
     *
     * @return Map<String, Object>
     */
    public static Map<String, Object> getGeneralCtrlTimer() {

        String requestURL = apiURLStr(17);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getGeneralCtrlTimer], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.18、修改总控定时计划（InterFaceType=18）   --页面手动调用
     *
     * @param in GeneralCtrlTimerIn
     * @return Map<String, Object>
     */
    public static Map<String, Object> updateGeneralCtrlTimer(GeneralCtrlTimerIn in) {

        String requestURL = apiURLStr(18);
        String rstJson = post(requestURL, in.toString());
        logger.debug(" [SeApiToolKit.updateGeneralCtrlTimer], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.19、查询总控全年排程（InterFaceType=19）
     *
     * @return YearCron
     */
    public static YearCron getGeneralCtrlCron() {

        String requestURL = apiURLStr(19);
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.getGeneralCtrlCron], response={}", rstJson);

        return JsonUtil.toObject(rstJson, YearCron.class);
    }

    /**
     * 3.20、修改总控全年排程（InterFaceType=20）
     *
     * @param in 全年排程
     * @return Map<String, Object>
     */

    public static Map<String, Object> updateGeneralCtrlCron(YearCron in) {

        String requestURL = apiURLStr(20);
        String rstJson = post(requestURL, in.toString());
        logger.debug(" [SeApiToolKit.updateGeneralCtrlCron], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }


    /**
     * //3.21、修改当天总控模式（InterFaceType=21）
     *
     * @param WorkMode 总控工作模式：  1-平日模式、2-节假日模式、3-重大节假日模式、其他数值为非法;
     * @return Map<String, Object>
     */
    public static Map<String, Object> updateDayCron(int WorkMode) {

        String requestURL = apiURLStr(21);
        requestURL += "&WorkMode=" + WorkMode;
        String rstJson = post(requestURL, "");
        logger.debug(" [SeApiToolKit.updateDayCron], response={}", rstJson);

        return JsonUtil.toMap(rstJson);
    }
}
