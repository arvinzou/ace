package com.huacainfo.ace.rvc.kedapi.manage;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @description: 会议管理api
 * @author: ArvinZou
 * @create: 2017-11-16 10:59
 */
public class MangeApi {
    private static Logger logger = Logger.getLogger(MangeApi.class);


    /**
     * 创建会议
     *
     * @param accountToken 有效token
     * @param jsonParams   请求参数
     * @return conf_id/fail
     */
    public static String create(String accountToken, String jsonParams, String cookies) {
        String params = "";
        try {
            params = URLEncoder.encode(jsonParams, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "fail";
        }

        Map<String, String> rtnMap = HttpKit.doPost(
                CommonKit.URI + "/api/v1/mc/confs?",
                "account_token=" + accountToken + "&params=" + params, cookies);

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
    public static String delete(String accountToken, String confId, String cookies) {
        Map<String, String> rtnMap = HttpKit.doPost(
                CommonKit.URI + "/api/v1/mc/confs/" + confId + "?",
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
