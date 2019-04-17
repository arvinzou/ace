package com.huacainfo.ace.glink.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.URLKit;
import com.huacainfo.ace.glink.api.pojo.fe.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SeApiToolKit
 * @Description 强电接口--调用工具
 * @Author Arvin Zou
 * @Date 2019/4/15 9:57
 */
public class SeApiToolKit {

    private static final String API_DOMAIN = "http://192.168.100.2?";
    private SeApiToolKit instance;

    /**
     * 单例模式
     */
    private SeApiToolKit() {

    }

    public SeApiToolKit newInstance() {
        if (instance == null) {
            return new SeApiToolKit();
        }

        return instance;
    }

    private Map<String, String> common(String interFaceType) {
        Map<String, String> map = new HashMap<>();
        map.clear();

        map.put("Appkey", "ProjectYalro");
        map.put("InterFaceType", interFaceType);

        return map;
    }

    private String parse(Map<String, String> p) {
        return URLKit.mapToStr(p);
    }


    /**
     * 3.1、获取项目区域数据（InterFaceType=1） -- 初始化一次，后期根据需求手动调用
     *
     * @return ProjectAreaOut
     */
    public ProjectAreaOut getAreaProjectInfo() {
        Map<String, String> params = common("1");
        String rstJson = HttpKit.get(API_DOMAIN + parse(params));

        return JsonUtil.toObject(rstJson, ProjectAreaOut.class);
    }

    /**
     * 3.2、获取配电箱基础数据（InterFaceType=2）    -- 初始化一次，后期根据需求手动调用
     *
     * @return JackBoxOut
     */
    public JackBoxOut getBaseNodeInfo() {
        Map<String, String> params = common("2");
        String rstJson = HttpKit.get(API_DOMAIN + parse(params));

        return JsonUtil.toObject(rstJson, JackBoxOut.class);
    }

    //3.3、获取单个配电箱监测数据（InterFaceType=3） -- 页面手动及时调用
    public Map<String, Object> getNodeMonitorSingleData(String nodeId) {
        Map<String, String> params = common("3");
        params.put("NodeID", nodeId);

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }

    /**
     * 3.4、获取多个配电箱监测数据（InterFaceType=4） -- 页面手动及时调用
     *
     * @param nodeGroup 配电箱ID组合，用英文','隔开
     * @return Map<String, Object>
     */
    public NodeMonitorDataOut getNodeMonitorListData(String nodeGroup) {
        Map<String, String> params = common("4");
        params.put("NodeGroup", nodeGroup);

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, NodeMonitorDataOut.class);
    }

    /**
     * 3.5、获取全部配电箱电表数据（InterFaceType=5）  -- 每天定时获取一次
     *
     * @return MeterBoxOut
     */
    public MeterBoxOut getAllMeterData() {
        Map<String, String> params = common("5");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, MeterBoxOut.class);
    }

    /**
     * 3.6、获取4G 路由器运行状态（InterFaceType=6） -- 页面手动调用
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> get4GRouterState() {
        Map<String, String> params = common("6");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }

    /**
     * 3.7、获取网关设备运行状态（InterFaceType=7）    -- 页面手动调用
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getGatewayState() {
        Map<String, String> params = common("7");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.8、获取逻辑区定义数据（InterFaceType=8）    -- 初始化一次，后期根据需求手动调用
     *
     * @return CustomAreaOut
     */
    public CustomAreaOut getCustomAreaInfo() {
        Map<String, String> params = common("8");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, CustomAreaOut.class);
    }

    /**
     * 3.9、获取区域任务数据（InterFaceType=9）  -- 页面手动调用
     *
     * @return AreaTaskOut
     */
    public AreaTaskOut getAreaTaskInfo() {
        Map<String, String> params = common("9");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, AreaTaskOut.class);
    }

    /**
     * 3.10、获取场景定义列表（InterFaceType=10） -- 初始化一次，后期根据需求手动调用
     *
     * @return PresetDataOut
     */
    public PresetDataOut getPresetData() {
        Map<String, String> params = common("10");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, PresetDataOut.class);
    }

    /**
     * 3.11、获取系统定时任务数据（InterFaceType=11） -- 初始化一次，后期根据需求手动调用
     *
     * @return TimerDataOut
     */
    public TimerDataOut getTimerData() {
        Map<String, String> params = common("11");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
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
    public Map<String, Object> executePreset(String AreaNO, String PresetNo, String AreaNodeID) {
        Map<String, String> params = common("12");
        params.put("AreaNO", AreaNO);
        params.put("PresetNo", PresetNo);
        params.put("AreaNodeID", AreaNodeID);

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.13、执行一键控制任务（InterFaceType=13）   --  页面手动调用
     *
     * @param TaskNO 任务序号
     * @return Map<String, Object>
     */
    public Map<String, Object> executeTask(String TaskNO) {
        Map<String, String> params = common("13");
        params.put("TaskNO", TaskNO);

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }

    //TODO 3.14、报警信息推送 -- 构建服务器，提供接收数据接口

    /**
     * 3.15、系统定时计划修改（InterFaceType=14）   --页面手动调用
     *
     * @param timerData 入参
     * @return Map<String, Object>
     */
    public Map<String, Object> updateTimer(TimerDataOut.TimerData timerData) {
        Map<String, String> params = common("14");

        String rstJson = HttpKit.post(API_DOMAIN + parse(params), timerData.toString());
        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.16、获取逻辑区当前场景（InterFaceType=15）  --页面手动调用
     *
     * @param AreaNodeID 区域编号
     * @return Map<String, Object>
     */
    public Map<String, Object> getCurrentPreset(String AreaNodeID) {
        Map<String, String> params = common("15");
        params.put("AreaNodeID", AreaNodeID);

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }

    /**
     * 3.17、查询总控定时计划（InterFaceType=17）   --页面手动调用
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> getGeneralCtrlTimer() {
        Map<String, String> params = common("17");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.18、修改总控定时计划（InterFaceType=18）   --页面手动调用
     *
     * @param in GeneralCtrlTimerIn
     * @return Map<String, Object>
     */
    public Map<String, Object> updateGeneralCtrlTimer(GeneralCtrlTimerIn in) {
        Map<String, String> params = common("18");

        String rstJson = HttpKit.post(API_DOMAIN + parse(params), in.toString());
        return JsonUtil.toMap(rstJson);
    }


    /**
     * 3.19、查询总控全年排程（InterFaceType=19）
     *
     * @return YearCron
     */
    public YearCron getGeneralCtrlCron() {
        Map<String, String> params = common("19");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toObject(rstJson, YearCron.class);
    }

    /**
     * 3.20、修改总控全年排程（InterFaceType=20）
     *
     * @param in 全年排程
     * @return Map<String, Object>
     */

    public Map<String, Object> updateGeneralCtrlCron(YearCron in) {
        Map<String, String> params = common("20");

        String rstJson = HttpKit.post(API_DOMAIN + parse(params), in.toString());
        return JsonUtil.toMap(rstJson);
    }


    /**
     * //3.21、修改当天总控模式（InterFaceType=21）
     *
     * @param WorkMode 总控工作模式：  1-平日模式、2-节假日模式、3-重大节假日模式、其他数值为非法;
     * @return Map<String, Object>
     */
    public Map<String, Object> updateDayCron(int WorkMode) {
        Map<String, String> params = common("21");
        params.put("WorkMode", WorkMode + "");

        String rstJson = HttpKit.get(API_DOMAIN + parse(params));
        return JsonUtil.toMap(rstJson);
    }
}
