package com.huacainfo.ace.glink.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.URLKit;
import com.huacainfo.ace.glink.api.pojo.fe.ProjectAreaOut;

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


    //3.1、获取项目区域数据（InterFaceType=1）
    public ProjectAreaOut getAreaProjectInfo() {
        Map<String, String> params = common("1");
        String rstJson = HttpKit.get(API_DOMAIN + parse(params));

        return JsonUtil.toObject(rstJson, ProjectAreaOut.class);
    }

    //3.2、获取配电箱基础数据（InterFaceType=2）
    public String getBaseNodeInfo() {
        Map<String, String> params = common("2");

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.3、获取单个配电箱监测数据（InterFaceType=3）
    public String getNodeInfo(String nodeId) {
        Map<String, String> params = common("3");
        params.put("NodeID", nodeId);

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.4、获取多个配电箱监测数据（InterFaceType=4）
    public String getNodeInfoList(String nodeGroup) {
        Map<String, String> params = common("4");
        params.put("NodeGroup", nodeGroup);

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.5、获取全部配电箱电表数据（InterFaceType=5）
    public String getNodeInfoAll(String nodeGroup) {
        Map<String, String> params = common("5");

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.6、获取4G 路由器运行状态（InterFaceType=6）
    public String get4GRouterState(String nodeGroup) {
        Map<String, String> params = common("6");

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.7、获取网关设备运行状态（InterFaceType=7）
    public String getGatewayState(String nodeGroup) {
        Map<String, String> params = common("7");

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.8、获取逻辑区定义数据（InterFaceType=8）
    public String getCustomAreaInfo(String nodeGroup) {
        Map<String, String> params = common("8");

        return HttpKit.get(API_DOMAIN + parse(params));
    }

    //3.9、获取区域任务数据（InterFaceType=9）
    public String getAreaJobInfo(String nodeGroup) {
        Map<String, String> params = common("9");

        return HttpKit.get(API_DOMAIN + parse(params));
    }
//3.10、获取场景定义列表（InterFaceType=10）
//3.11、获取系统定时任务数据（InterFaceType=11）
//3.12、执行区域场景指令（InterFaceType=12）
//3.13、执行一键控制任务（InterFaceType=13）
//3.14、报警信息推送
//3.15、系统定时计划修改（InterFaceType=14）
//3.16、获取逻辑区当前场景（InterFaceType=15）
//3.17、查询总控定时计划（InterFaceType=17）
//3.18、修改总控定时计划（InterFaceType=18）
//3.19、查询总控全年排程（InterFaceType=19）
//3.20、修改总控全年排程（InterFaceType=20）
//3.21、修改当天总控模式（InterFaceType=21）
}
