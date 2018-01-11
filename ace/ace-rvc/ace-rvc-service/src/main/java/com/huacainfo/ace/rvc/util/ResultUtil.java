package com.huacainfo.ace.rvc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arvin on 2017/12/4.
 */
public class ResultUtil {

    public static final String FAIL = "fail";

    public static final String SUCCESS = "success";

    public static Map<String, Object> success(Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put("return_code", "success");
        map.put("result_code", 1);
        map.put("data", object);

        return map;
    }

    public static Map<String, Object> fail(int errorCode, String errorMsg) {
        Map<String, Object> map = new HashMap<>();
        map.put("return_code", "success");
        map.put("result_code", -1);
        map.put("errorCode", errorCode);
        map.put("errorMsg", errorMsg);

        return map;
    }
}
