package com.huacainfo.ace.glink.api;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;
import com.huacainfo.ace.glink.api.pojo.le.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeApiToolKit
 * @Description 弱电接口--调用工具
 * @Author Arvin Zou
 * @Date 2019/4/15 9:57
 */
public class LeApiToolKit {
    private static final String domain = PropertyUtil.getProperty("le.api.url");//
    static Logger logger = LoggerFactory.getLogger(LeApiToolKit.class);

    /**
     * 单例模式
     */
    private LeApiToolKit() {

    }

    private static String get(String url) {
        logger.debug("[GET] request url={}", url);
        String r = HttpKit.get(url);
        logger.debug("response={}", r);
        return r;
    }

    /**
     * 灯光策略下发
     *
     * @param params 下发参数
     * @return LeBaseOut
     */
    public static String lightStrategy(LightStrategyIn params) {
        Map<String, Object> request = new HashMap<>();
        request.put("pattern", params.getPattern());
        request.put("strategy", params.getStrategy());
        request.put("area", params.getArea());
        request.put("startTime", params.getStartTime());
        request.put("stopTime", params.getStopTime());
        //参数准备
        switch (params.getPattern()) {
            case LightStrategyIn.MODE_SCHEDULE://日程模式
                //模式专有
                request.put("isWeek", params.getIsWeek() + "");
                request.put("weeks", params.getWeeks().split(","));
                request.put("isMonth", params.getIsMonth() + "");
                request.put("months", params.getMonths().split(","));
                //
                request.put("startDate", "");
                request.put("stopDate", "");
                request.put("specialDate", "");
                break;
            case LightStrategyIn.MODE_HOLIDAY://假日模式
                //模式专有
                request.put("startDate", params.getStartDate());
                request.put("stopDate", params.getStopDate());
                //
                request.put("isWeek", "0");
                request.put("weeks", new String[]{});
                request.put("isMonth", "0");
                request.put("months", new String[]{});
                request.put("specialDate", "");
                break;
            case LightStrategyIn.MODE_EVENT://事件模式
                request.put("specialDate", params.getSpecialDate());
                //
                request.put("isWeek", "0");
                request.put("weeks", new String[]{});
                request.put("isMonth", "0");
                request.put("months", new String[]{});
                request.put("startDate", "");
                request.put("stopDate", "");
                break;
            default:
                LeBaseOut out = new LeBaseOut();
                out.setCode(400);
                out.setMessage("策略模式设置有误！");
                return out.toString();
        }
        String path = "/wh/limplight/control/lightStrategy";

        return post(domain + path, JsonUtil.toJson(request));
    }

    private static String post(String url, String json) {
        logger.debug("post.url:{}\n" + "post.json:{}", url, json);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String r = HttpKit.post(url, json, headers);

        logger.debug("post.response: {}", r);

        return r;
    }


    /**
     * 获取区级平台信息接口
     *
     * @return code    int	Y	返回码: 200: 成功;400: 失败
     * message	String	N	返回描述
     * data	Object	N	返回数据
     * area	String	Y	分区编号
     * status	int	Y	平台状态(1:正常 2:异常)
     * infos	ErrData数组	N	平台异常数据集合
     * type	int	Y	异常类型（1:服务器异常 2：网络异常 3：其它异常）
     * reason	String	Y	异常的具体说明
     */
    public static HeartOut heart() {
        String path = "/wh/status/control/heart";

        String rstJson = get(domain + path);// HttpKit.get();

        return JsonUtil.toObject(rstJson, HeartOut.class);
    }

    /**
     * 分区灯光启动仪式
     *
     * @param execute 启动仪式操作(1:启动 2：停止)
     * @return String
     */
    public static String completionCeremony(int execute) {
        String path = "/wh/start/control/completionCeremony?execute=" + execute;
        return HttpKit.get(domain + path);
    }

    public static void lightStrategy() {
//{
//"area":"0000",
//"pattern":"1",
//"startTime":"20190107",
//"stopTime":"20190108",
//"strategy":"01932EF",
//"isWeek":"1",
//"weeks":[1,2,3,4,5],
//"isMonth":"0",
//"months":[],
//"startDate":"",
//"stopDate":"",
//"specialDate":""
//}

        LightStrategyIn p = new LightStrategyIn("01932EF", "0000", "20190107", "20190107",
                1, "[1,2,3]", 0, "[]");
        System.out.println(p.toString());
        System.out.println(LeApiToolKit.lightStrategy(p));
    }

    /**
     * //模式的紧急停止(恢复)
     *
     * @param mode 1：停止  2:恢复
     * @param area 分区编号（2）
     * @return String
     */
    public static String stopRegain(int mode, String area) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/control/stopRegain?");
        path.append("area=").append(area);
        path.append("&mode=").append(mode);

        return get(path.toString());
    }

    /**
     * 获取分区(站点或建筑物)执行结果信息
     *
     * @param type 请求的类型(1:分区 2 ：建筑物)
     * @param num  分区或建筑物(type=1时为分区 type=2时为建筑物)编号
     * @return String
     */
    public static StatsOut stats(int type, String num) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/control/stats?");
        path.append("type=").append(type);
        path.append("&num=").append(num);

        String rstJson = get(path.toString());
        return JsonUtil.toObject(rstJson, StatsOut.class);
    }

    /**
     * 关键变化数据上传  -- 被新接口替换
     *
     * @param params 请求入参
     * @return String
     */
    @Deprecated
    public static String change(ChangeIn params) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/data/change?");
        return post(path.toString(), params.toString());
    }

    @Deprecated
    public static String change(int type) {
        ChangeIn in = new ChangeIn();
        in.setArea("0000");
        //
        ChangeIn.NodeInfo nodeInfo = new ChangeIn.NodeInfo();
        nodeInfo.setNum("123123");
        switch (type) {
            case 1:
                //group1
                nodeInfo.setType(1);
                nodeInfo.setIsOnline(1);
                break;
            case 2:
                //group2
                nodeInfo.setType(2);
                List<ChangeIn.Point> points = new ArrayList<>();
                ChangeIn.Point p = new ChangeIn.Point();
                p.setPointNum("00001");
                p.setPointStatus(2);
                points.add(p);
                nodeInfo.setPoints(points);
                break;
            case 3:
                //group3
                nodeInfo.setType(3);
                nodeInfo.setProgram("示例节目1");
                break;
            default:
                //group1
                nodeInfo.setType(1);
                nodeInfo.setIsOnline(1);
                break;
        }
        //
        List<ChangeIn.NodeInfo> nodeInfos = new ArrayList<>();
        nodeInfos.add(nodeInfo);
        in.setInfos(nodeInfos);


        return change(in);
    }


    /**
     * //    武汉设备故障数量接口
     * date >= beginDate && date < endDate
     *
     * @param beginDate 开始日期（格式：yyyyMMdd)，不填写默认20190101
     * @param endDate   结束日期（格式：yyyyMMdd)，不填写默认当前日期的前一日
     * @return data
     */
    public static LightBrokenSumOut lightBrokenSum(String beginDate, String endDate) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/lightBrokenSum");

        Map<String, String> params = new HashMap<>();
        params.put("BeginDate", beginDate);
        params.put("EndDate", endDate);

        String rstJson = post(path.toString(), JsonUtil.toJson(params));

        return JsonUtil.toObject(rstJson, LightBrokenSumOut.class);
    }


    /**
     * 武汉建筑物总数接口
     *
     * @return data
     */
    public static String getBuildingCount() {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/GetBulidingCount");
        return get(path.toString());
    }

    /**
     * 武汉设备状态接口
     *
     * @return data
     */
    public static LampStatusOut getLampStatus() {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/GetLampStatus");
        String rstJson = get(path.toString());

        return JsonUtil.toObject(rstJson, LampStatusOut.class);
    }

    /**
     * 武汉设备故障情况接口
     *
     * @param date 查询日期: yyyMMdd
     * @return data
     */
    public static GetBrokenLampDetailOut getBrokenLampDetail(String date) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/GetBrokenLampDetail?");
        path.append("Date=").append(date);
        //
        String rstJson = get(path.toString());

        return JsonUtil.toObject(rstJson, GetBrokenLampDetailOut.class);
    }

    /**
     * 武汉策略信息详情接口
     *
     * @return data
     */
    public static StrategysDetailOut strategysDetail() {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/StrategysDetail");
        String rstJson = get(path.toString());

        return JsonUtil.toObject(rstJson, StrategysDetailOut.class);
    }

    /**
     * 武汉建筑物信息详情接口
     *
     * @return data
     */
    public static GetBulidingDetailOut getBuildingDetail(String buildingNo) {
        StringBuilder path = new StringBuilder();
        path.append(domain).append("/wh/limplight/GetBulidingDetail");
        if (StringUtil.isNotEmpty(buildingNo)) {
            path.append("?buildingNo=").append(buildingNo);
        }
        String rstJson = get(path.toString());

        return JsonUtil.toObject(rstJson, GetBulidingDetailOut.class);
    }
}
