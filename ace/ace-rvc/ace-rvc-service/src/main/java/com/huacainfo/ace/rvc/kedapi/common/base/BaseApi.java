package com.huacainfo.ace.rvc.kedapi.common.base;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;

/**
 * Created by Arvin on 2017/11/29.
 */
public class BaseApi {

    /**
     * 同一回复
     *
     * @param respStr json串
     * @return success/fail
     */
    public static String response(String respStr) {
        if (JsonUtil.toMap(respStr).get("success") == 1) {
            return CommonKit.success();
        } else {
            return CommonKit.fail();
        }
    }
}
