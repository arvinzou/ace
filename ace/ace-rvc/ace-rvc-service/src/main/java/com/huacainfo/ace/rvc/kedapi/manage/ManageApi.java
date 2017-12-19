package com.huacainfo.ace.rvc.kedapi.manage;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.kedapi.authorize.AuthorizeApi;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.UrlKit;
import com.huacainfo.ace.rvc.kedapi.manage.model.create.CreateRequest;
import com.huacainfo.ace.rvc.kedapi.manage.model.get.PreConfListResp;
import com.huacainfo.ace.rvc.kedapi.manage.model.get.PreConfResp;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 会议管理api
 * @author: ArvinZou
 * @create: 2017-11-16 10:59
 */
public class ManageApi {
    private static Logger logger = Logger.getLogger(ManageApi.class);

    private ManageApi() {

    }

    /***
     * 名称	获取预约会议信息
     URI	/api/v1/mc/booked_confs/{conf_id}
     方法	GET
     说明	获取预约会议信息
     *
     * @param token
     * @param cookies
     * @return
     */
    public static PreConfResp get(String confId, String token, String cookies) {
        String uri = AuthorizeApi.URI + "/api/v1/mc/booked_confs/" + confId;

        String respStr = HttpKit.doGet(uri, "account_token=" + token, cookies);

        logger.debug("ManageApi.get:" + respStr);
        return JsonUtil.toObject(respStr, PreConfResp.class);
    }

    /**
     * 名称	获取预约会议列表
     * URI	/api/v1/mc/booked_confs
     * 方法	GET
     * 说明	获取预约会议列表
     *
     * @param token   登陆token
     * @param cookies
     * @return list
     */

    public static List<PreConfResp> getList(String token, String cookies) {
        String uri = AuthorizeApi.URI + "/api/v1/mc/booked_confs";
        Map<String, Object> params = new HashMap<>();
        params.put("account_token", token);
        String respStr = HttpKit.doGet(uri, UrlKit.getUrlParamsByMap(params), cookies);
        logger.debug("ManageApi.getList:" + respStr);
        PreConfListResp listResp = JsonUtil.toObject(respStr, PreConfListResp.class);

        return listResp.getBooked_confs();
    }

    /**
     * 名称	获取预约会议列表
     * URI	/api/v1/mc/booked_confs
     * 方法	GET
     * 说明	获取预约会议列表
     *
     * @param start     默认为0，即从符合条件的数据中的第几行取数据，0为第一行数据
     * @param count     默认为10，即在包含start在内的后count条数据，如果参数为0表示获取所有
     * @param startTime 开始时间，格式为ISO8601:2000标准，不填表示从服务器当前时间开始
     * @param endTime   结束时间，格式为ISO8601:2000标准，不填表示从开始时间以后的所有
     * @param token     登陆token
     * @param cookies
     * @return list
     */

    public static List<PreConfResp> getList(int start, int count, String startTime, String endTime,
                                            String token, String cookies) {
        String uri = AuthorizeApi.URI + "/api/v1/mc/booked_confs";
        Map<String, Object> params = new HashMap<>();
        params.put("account_token", token);
        start = start < 0 ? 0 : start;
        params.put("start", start);
        count = count < 10 ? 10 : count;
        params.put("count", count);
        params.put("order", 0);
        if (!StringUtils.isEmpty(startTime)) {
            params.put("start_time", startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            params.put("end_time", endTime);
        }

        String respStr = HttpKit.doGet(uri, UrlKit.getUrlParamsByMap(params), cookies);
        logger.debug("ManageApi.getList:" + respStr);

        PreConfListResp listResp = JsonUtil.toObject(respStr, PreConfListResp.class);

        return listResp.getBooked_confs();
    }

    /**
     * 创建会议
     *
     * @param token   有效token
     * @param request 请求参数
     * @return conf_id/fail
     */
    public static String create(String token, String cookies, CreateRequest request) {
        String params = "";
        try {
            params = URLEncoder.encode(request.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "fail";
        }

        Map<String, String> rtnMap = HttpKit.doPost(
                AuthorizeApi.URI + "/api/v1/mc/confs?",
                "account_token=" + token + "&params=" + params, cookies);

        logger.debug(DateUtil.getNow() + " MangeApi.create.response:" + rtnMap.toString());
//        {
//            "success": 1,
//                "conf_id": "112233",
//                "description": "操作成功"
//        }
        Map map = JsonUtil.toMap(rtnMap.get("resp"));

        if (map.get("success") == 1) {
            return (String) map.get("conf_id");
        } else {
            return CommonKit.fail();
        }
    }

    /**
     * 结束会议
     *
     * @param accountToken 有效token
     * @param confId       会议ID
     * @return success/fail
     */
    public static String delete(String confId, String accountToken, String cookies) {
        Map<String, String> rtnMap = HttpKit.doPost(
                AuthorizeApi.URI + "/api/v1/mc/confs/" + confId + "?",
                "account_token=" + accountToken + "&_method=delete", cookies);
        logger.debug("confId<" + confId + "> delete:" + rtnMap.toString());
//        {
//            "success": 1,
//                "description": "操作成功"
//        }
//        {
//            "success": 0,
//                "error_code": 0,
//                "description": "系统处理出错"
//        }
        if (JsonUtil.toMap(rtnMap.get("resp")).get("success") == 1) {
            return "success";
        } else {
            return "fail";
        }
    }

}
