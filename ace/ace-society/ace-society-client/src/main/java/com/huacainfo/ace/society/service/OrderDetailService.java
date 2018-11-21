package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.vo.OrderDetailQVo;
import com.huacainfo.ace.society.vo.OrderDetailVo;

/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单详情)
 */
public interface OrderDetailService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <OrderDetailVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    PageResult
            <OrderDetailVo> findOrderDetailList(OrderDetailQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertOrderDetail
     * @Description: TODO(添加订单详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse insertOrderDetail(OrderDetail obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateOrderDetail
     * @Description: TODO(更新订单详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse updateOrderDetail(OrderDetail obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectOrderDetailByPrimaryKey
     * @Description: TODO(获取订单详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<OrderDetail>
     * @author: Arvin
     * @version: 2018-09-17
     */
    SingleResult
            <OrderDetailVo> selectOrderDetailByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteOrderDetailByOrderDetailId
     * @Description: TODO(删除订单详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    MessageResponse deleteOrderDetailByOrderDetailId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单详情)
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
