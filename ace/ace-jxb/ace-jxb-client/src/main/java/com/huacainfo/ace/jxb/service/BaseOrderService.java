package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.BaseOrder;
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
    PageResult
            <BaseOrderVo> findBaseOrderList(BaseOrderQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

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
    SingleResult
            <BaseOrderVo> selectBaseOrderByPrimaryKey(String id) throws Exception;

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

}
