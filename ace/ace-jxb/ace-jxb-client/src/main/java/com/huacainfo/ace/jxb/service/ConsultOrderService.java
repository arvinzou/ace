package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.ConsultOrder;
import com.huacainfo.ace.jxb.vo.ConsultOrderQVo;
import com.huacainfo.ace.jxb.vo.ConsultOrderVo;

/**
 * @author: Arvin
 * @version: 2018-07-25
 * @Description: TODO(咨询预约订单详情)
 */
public interface ConsultOrderService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询预约订单详情分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ConsultOrderVo>
     * @author: Arvin
     * @version: 2018-07-25
     */
    PageResult
            <ConsultOrderVo> findConsultOrderList(ConsultOrderQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertConsultOrder
     * @Description: TODO(添加咨询预约订单详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse insertConsultOrder(ConsultOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateConsultOrder
     * @Description: TODO(更新咨询预约订单详情)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse updateConsultOrder(ConsultOrder obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectConsultOrderByPrimaryKey
     * @Description: TODO(获取咨询预约订单详情)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ConsultOrder>
     * @author: Arvin
     * @version: 2018-07-25
     */
    SingleResult
            <ConsultOrderVo> selectConsultOrderByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteConsultOrderByConsultOrderId
     * @Description: TODO(删除咨询预约订单详情)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-25
     */
    MessageResponse deleteConsultOrderByConsultOrderId(String id, UserProp userProp) throws Exception;

}
