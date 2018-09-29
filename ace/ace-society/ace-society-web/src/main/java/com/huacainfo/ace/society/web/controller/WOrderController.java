package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
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
     * @param info 订单信息
     * @return ResultResponse
     */
    @RequestMapping("/create")
    public ResultResponse create(OrderInfoVo info) throws Exception {
        if (!StringUtil.areNotEmpty(info.getPayType(), info.getReceiveType())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        Userinfo u = getUserInfo(info.getUserId());
        if (u == null || StringUtil.isEmpty(u.getUnionid())) {
            return new ResultResponse(ResultCode.FAIL, "微信授权失败");
        }

        return orderInfoService.create(info);
    }
}
