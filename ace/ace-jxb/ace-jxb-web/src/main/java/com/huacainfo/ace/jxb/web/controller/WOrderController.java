package com.huacainfo.ace.jxb.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.model.OrderComplaint;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
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
    @Autowired
    private BisMsgNoticeService bisMsgNoticeService;

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
     * 查询订单
     *
     * @param findType  查询类型 1-“我”的订单，2-属于“我”的订单
     * @param condition 查询条件
     * @param page      分页查询条件
     * @return
     * @throws Exception
     */
    @RequestMapping("/profitFindList")
    public ResultResponse profitFindList(String findType, BaseOrderQVo condition, PageParamNoChangeSord page) throws Exception {
        if (StringUtil.isEmpty(findType)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        String consumerId = condition.getConsumerId();
        String bisId = condition.getBusinessId();
        //属于“我”的订单
        Userinfo userinfo = getCurUserinfo();
        if (null == userinfo || StringUtil.isEmpty(userinfo.getUnionid())) {
            return new ResultResponse(ResultCode.FAIL, "缺少商家主键");
        }
        condition.setBusinessId(userinfo.getUnionid());

        PageResult<BaseOrderVo> pageResult = baseOrderService.
                profitFindList(condition, page.getStart(), page.getLimit(), page.getOrderBy());

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

    /**
     * 商品购买记录查询
     *
     * @param commodityId 商品ID
     * @param consumerId  用户ID，可选
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/paidQuery")
    public ResultResponse paidQuery(String commodityId, String consumerId) throws Exception {
        if (!StringUtil.areNotEmpty(commodityId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        if (StringUtil.isEmpty(consumerId)) {
            consumerId = getUnionid();
            if (StringUtil.isEmpty(consumerId)) {
                return new ResultResponse(ResultCode.FAIL, "微信授权失败");
            }
        }

        return baseOrderService.paidQuery(commodityId, consumerId);
    }

    private String getUnionid() {
        Userinfo userinfo = getCurUserinfo();
        return null == userinfo ? "" : userinfo.getUnionid();
    }

    /**
     * 付款成功消息
     *
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/paidBisMsg")
    public ResultResponse paidBisMsg(String orderId) throws Exception {
        if (!StringUtil.areNotEmpty(orderId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return bisMsgNoticeService.paySuccess(orderId);
    }


    /**
     * 提交投诉
     */
    @RequestMapping("/submitComplaint")
    public ResultResponse submitComplaint(OrderComplaint complaint, String unionId) throws Exception {
        if (!StringUtil.areNotEmpty(complaint.getOrderId(), complaint.getContent())) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        unionId = StringUtil.isEmpty(unionId) ? getUnionid() : unionId;
        complaint.setUserId(unionId);

        return baseOrderService.submitComplaint(complaint);
    }

    /**
     * 创建打赏订单
     *
     * @param unionid 用户ID， 可选
     * @param data    订单参数，必选 咨询师ID,打赏金额(元) Demo: {"payMoney":"1","businessId":"cccc" }
     * @return ResultResponse
     * @throws Exception
     */
    @RequestMapping("/createTipOrder")
    public ResultResponse createTipOrder(String unionid, String data) throws Exception {
        if (null == data) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        if (StringUtil.isEmpty(unionid)) {
            Userinfo userinfo = getCurUserinfo();
            unionid = userinfo.getUnionid();
        }

        try {
            return baseOrderService.createTipOrder(unionid, data);
        } catch (CustomException e) {
            return new ResultResponse(ResultCode.FAIL, e.getMsg());
        }
    }
}
