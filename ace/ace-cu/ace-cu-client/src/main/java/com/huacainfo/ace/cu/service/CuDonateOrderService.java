package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.cu.model.CuDonateOrder;
import com.huacainfo.ace.cu.vo.CuDonateOrderQVo;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;

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
}
