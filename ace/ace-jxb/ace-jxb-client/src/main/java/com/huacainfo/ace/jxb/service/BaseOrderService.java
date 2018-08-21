package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.model.JxbCallBackLog;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(统一订单)
 */
public interface BaseOrderService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(统一订单分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <BaseOrderVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    ResultResponse findBaseOrderListSencond(BaseOrderQVo condition, int start, int limit, String orderBy) throws Exception;


    PageResult<BaseOrderVo> findBaseOrderList(BaseOrderQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertBaseOrder
     * @Description: TODO(添加统一订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertBaseOrder(BaseOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateBaseOrder
     * @Description: TODO(更新统一订单)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateBaseOrder(BaseOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取统一订单)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult<BaseOrderVo> selectBaseOrderByPrimaryKey(String id) throws Exception;

    ResultResponse orderInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteBaseOrderByBaseOrderId
     * @Description: TODO(删除统一订单)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteBaseOrderByBaseOrderId(String id, UserProp userProp) throws Exception;

    /**
     * 创建订单
     *
     * @param unionid
     * @param data    数据示例：
     *                {
     *                --订单基本情况  参考 BaseOrder.java
     *                "base": {
     *                "businessId": "businessId",
     *                "category": "1",
     *                "commodityId": "commodityId",
     *                "consumerId": "consumerId"
     *                },
     *                --预约详情 参考 ConsultOrder.java
     *                "consult": {
     *                "age": 1,
     *                "info": "Info",
     *                "name": "Name",
     *                "relationship": "Relationship",
     *                "secName": "SecName",
     *                "secTel": "SecTel",
     *                "sex": "Sex",
     *                "tel": "Tel18000"
     *                }
     *                }
     * @return ResultResponse
     */
    ResultResponse create(String unionid, String data) throws Exception;

    /**
     * 记录回调日志
     *
     * @param callBackLog
     * @return
     */
    int insertCallBackLog(JxbCallBackLog callBackLog);

    /**
     * 付款完成逻辑
     *
     * @param callBackLog 支付日志
     * @param payType     支付方式
     * @return 处理结果
     */
    ResultResponse pay(JxbCallBackLog callBackLog, int payType);

    /**
     * 订单金额校验
     *
     * @param orderId 订单ID
     * @param Amount  订单金额
     * @return
     */
    ResultResponse checkAmount(String orderId, String Amount);

    /**
     * 商品购买查询
     *
     * @param commodityId 商品ID
     * @param consumerId  用户ID，可选
     * @return ResultResponse
     * @throws Exception
     */
    ResultResponse paidQuery(String commodityId, String consumerId);
}
