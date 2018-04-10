package com.huacainfo.ace.jxb.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Order;
import com.huacainfo.ace.jxb.vo.OrderVo;
import com.huacainfo.ace.jxb.vo.OrderQVo;
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(订单)
 */
public interface OrderService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(订单分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<OrderVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract PageResult<OrderVo> findOrderList(OrderQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertOrder
	    * @Description:  TODO(添加订单)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse insertOrder(Order obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateOrder
	    * @Description:  TODO(更新订单)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse updateOrder(Order obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectOrderByPrimaryKey
	    * @Description:  TODO(获取订单)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Order>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract SingleResult<OrderVo> selectOrderByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteOrderByOrderId
	    * @Description:  TODO(删除订单)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	public abstract MessageResponse deleteOrderByOrderId(String id,UserProp userProp) throws Exception;

	
}
