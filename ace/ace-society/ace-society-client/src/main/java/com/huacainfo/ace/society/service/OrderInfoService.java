package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.OrderInfo;
import com.huacainfo.ace.society.vo.OrderInfoQVo;
import com.huacainfo.ace.society.vo.OrderInfoVo;

/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单管理)
 */
public interface OrderInfoService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrderInfoVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    PageResult
            <OrderInfoVo> findOrderInfoList(OrderInfoQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertOrderInfo
     * @Description: TODO(添加订单管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse insertOrderInfo(OrderInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateOrderInfo
     * @Description: TODO(更新订单管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse updateOrderInfo(OrderInfo obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectOrderInfoByPrimaryKey
     * @Description: TODO(获取订单管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrderInfo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    SingleResult
            <OrderInfoVo> selectOrderInfoByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteOrderInfoByOrderInfoId
     * @Description: TODO(删除订单管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse deleteOrderInfoByOrderInfoId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
