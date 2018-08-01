package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/7/30 11:47
 * @Description:
 */
@RestController
@RequestMapping("/www/order")
public class WOrderController extends JxbBaseController {

    @Autowired
    private BaseOrderService baseOrderService;

    /**
     * 创建订单
     *
     * @param data 数据示例：
     *             {
     *             --订单基本情况  参考 BaseOrder.java
     *             "base": {
     *             "businessId": "businessId",
     *             "category": "1",
     *             "commodityId": "commodityId",
     *             "consumerId": "consumerId"
     *             },
     *             --预约详情 参考 ConsultOrder.java
     *             "consult": {
     *             "age": 1,
     *             "info": "Info",
     *             "name": "Name",
     *             "relationship": "Relationship",
     *             "secName": "SecName",
     *             "secTel": "SecTel",
     *             "sex": "Sex",
     *             "tel": "Tel18000"
     *             }
     *             }
     * @return ResultResponse
     */
    @RequestMapping("/create")
    public ResultResponse create(String unionid, String data) throws Exception {
        if (null == data) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        if (StringUtil.isEmpty(unionid)) {
            Userinfo userinfo = getCurUserinfo();
            unionid = userinfo.getUnionid();
        }

        try {
            return baseOrderService.create(unionid, data);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }
}
