package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
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


    /**
     * 查询订单
     *
     * @param findType  查询类型 1-“我”的订单，2-属于“我”的订单
     * @param condition 查询条件
     * @param page      分页查询条件
     * @return
     * @throws Exception
     */
    @RequestMapping("/findList")
    public ResultResponse findList(String findType, BaseOrderQVo condition, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(findType)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        String consumerId = condition.getConsumerId();
        String bisId = condition.getBusinessId();
        //“我”的订单
        if ("1".equals(findType) && StringUtil.isEmpty(consumerId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo || StringUtil.isEmpty(userinfo.getUnionid())) {
                return new ResultResponse(ResultCode.FAIL, "缺少客户主键");
            }
            condition.setConsumerId(userinfo.getUnionid());
        }
        //属于“我”的订单
        else if ("2".equals(findType) && StringUtil.isEmpty(bisId)) {
            Userinfo userinfo = getCurUserinfo();
            if (null == userinfo || StringUtil.isEmpty(userinfo.getUnionid())) {
                return new ResultResponse(ResultCode.FAIL, "缺少商家主键");
            }
            condition.setBusinessId(userinfo.getUnionid());
        }

        PageResult<BaseOrderVo> pageResult = baseOrderService.
                findBaseOrderList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", pageResult);
    }

    /**
     * 查询订单 详情
     *
     * @return
     */
    @RequestMapping("/findDetail")
    public ResultResponse findDetail(String orderId) throws Exception {
        if (StringUtil.isEmpty(orderId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        BaseOrderVo orderVo = baseOrderService.selectBaseOrderByPrimaryKey(orderId).getValue();

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", orderVo);
    }
}
