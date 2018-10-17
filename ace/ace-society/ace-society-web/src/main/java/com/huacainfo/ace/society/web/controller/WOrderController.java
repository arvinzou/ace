package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.society.service.OrderInfoService;
import com.huacainfo.ace.society.vo.OrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/9/19 11:17
 * @Description:
 */
@RestController
@RequestMapping("/www/order")
public class WOrderController extends SocietyBaseController {

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 创建订单
     *
     * @param params 订单信息
     * @return ResultResponse
     */
    @RequestMapping("/create")
    public ResultResponse create(String params, String unionId) throws Exception {
        WxUser wxUser = getCurWxUser();//小程序用户
        if (null == wxUser && StringUtil.isEmpty(unionId)) {
            return new ResultResponse(ResultCode.FAIL, "微信鉴权失败");
        }
        unionId = StringUtil.isNotEmpty(unionId) ? unionId : wxUser.getUnionId();

        if (StringUtil.isEmpty(params)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }


        OrderInfoVo info = JsonUtil.toObject(params, OrderInfoVo.class);
        info.setUserId(unionId);

        return orderInfoService.create(info);
    }
}
