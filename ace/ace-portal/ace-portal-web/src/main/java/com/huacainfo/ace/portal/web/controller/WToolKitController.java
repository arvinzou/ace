package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.URLKit;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/12/28 16:30
 * @Description:
 */
@RestController
@RequestMapping("/www/toolKit")
public class WToolKitController extends PortalBaseController {

    @Autowired
    TaskCmccService taskCmccService;

    @RequestMapping("/sms")
    public MessageResponse sms(String mobile, String msg) throws Exception {
        msg = URLKit.strDecoder(msg, URLKit.UTF_8);
        TaskCmcc obj = new TaskCmcc();
        Map<String, Object> params = new HashMap<>();
        params.put("taskName", "账号" + mobile);
        params.put("msg", msg);
        params.put("tel", mobile + "," + mobile + ";");
        CommonBeanUtils.copyMap2Bean(obj, params);

        return taskCmccService.insertTaskCmcc(obj);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello, it's me [police school]";
    }

    @RequestMapping("/ocridcard")
    public ResultResponse ocridcard(String imgUrl) {
        if (StringUtil.isEmpty(imgUrl)) {
            return new ResultResponse(ResultCode.FAIL, "缺少图片URL地址");
        }

        Map<String, String> map = new HashMap<>();
        map.put("api_key", "-5Wf1CueJ8FffHLeEap4RtVOE77P6IQT");
        map.put("api_secret", "dxWQqNdaXugnohd021ba1Cu_g4tfLmW3");
        map.put("image_url", imgUrl);

        String url = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard?" + URLKit.mapToStr(map);
        Map<String, Object> rst = JsonUtil.toMap(HttpKit.post(url, ""));

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS", rst);
    }
}
