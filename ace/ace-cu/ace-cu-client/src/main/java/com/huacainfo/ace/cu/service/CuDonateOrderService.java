package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CcbCallbackLog;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.model.WxPayLog;
import com.huacainfo.ace.cu.vo.CuDonateOrderQVo;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;

import java.math.BigDecimal;

/**
 * @author: Arvin
 * @version: 2018-06-14
 * @Description: TODO(捐款支付订单)
 */
public interface CuDonateOrderService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(捐款支付订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuDonateOrderVo>
     * @author: Arvin
     * @version: 2018-06-14
     */
    PageResult<CuDonateOrderVo> findCuDonateOrderList(CuDonateOrderQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCuDonateOrder
     * @Description: TODO(添加捐款支付订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse insertCuDonateOrder(CuDonateOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCuDonateOrder
     * @Description: TODO(更新捐款支付订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse updateCuDonateOrder(CuDonateOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCuDonateOrderByPrimaryKey
     * @Description: TODO(获取捐款支付订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuDonateOrder>
     * @author: Arvin
     * @version: 2018-06-14
     */
    SingleResult<CuDonateOrderVo> selectCuDonateOrderByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCuDonateOrderByCuDonateOrderId
     * @Description: TODO(删除捐款支付订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-14
     */
    MessageResponse deleteCuDonateOrderByCuDonateOrderId(String id, UserProp userProp) throws Exception;

    /**
     * 创建捐款订单
     *
     * @param data 参考 CuDonateOrderVo.java对象
     * @return
     * @throws Exception
     */
    ResultResponse createDonateOrder(CuDonateOrderVo data);


    /**
     * 订单校验
     *
     * @param attach 附加数据 --  此处存放 cu_donate_order.id
     * @param fee    支付金额
     * @return
     */
    ResultResponse orderCheck(String attach, String fee);


    /**
     * 订单支付逻辑
     *
     * @param orderId   订单ID
     * @param payType   支付方式
     * @param payAmount 支付金额
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2018/9/7 16:41
     */
    ResultResponse pay(String orderId, String payType, BigDecimal payAmount);

    int insertWxPayLog(WxPayLog wxPayLog);

    /**
     * 建行龙支付回调日志
     *
     * @param params 通知参数
     * @return 处理结果
     */
    ResultResponse ccbCallBack(CcbCallbackLog params);
}
