package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.taa.model.RoadGps;
import com.huacainfo.ace.taa.service.RoadGpsService;
import com.huacainfo.ace.taa.service.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2019/1/12 11:36
 * @Description:
 */
@RestController
@RequestMapping("/www/road")
public class WRoadController extends TaaBaseController {
    @Autowired
    private RoadService roadService;
    @Autowired
    private RoadGpsService roadGpsService;

    /**
     * 路段采集
     *
     * @param jsonData List<RoadGps> 数据数组 - json格式字符串
     *                 roadSectionId 所属路段
     *                 latitude      纬度
     *                 longitude     经度
     * @param uid      操作用户ID，可选
     * @return ResultResponse
     */
    @RequestMapping("/gather")
    public ResultResponse gather(String jsonData, String uid) throws Exception {
        if (CommonUtils.isBlank(jsonData)) {
            return new ResultResponse(ResultCode.FAIL, "缺少加入参数");
        }

        //微信鉴权信息 --小程序
        WxUser user = getCurWxUser();
        if (StringUtil.isNotEmpty(uid)) {
            user = new WxUser();
            user.setUnionId(uid);
            user.setNickName("test");
        }
        if (user == null) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }

        List<RoadGps> list = JsonUtil.toList(jsonData, RoadGps.class);
        MessageResponse ms = roadGpsService.insertRoadGps(list, parseUser(user));

        return new ResultResponse(ms);
    }
}
