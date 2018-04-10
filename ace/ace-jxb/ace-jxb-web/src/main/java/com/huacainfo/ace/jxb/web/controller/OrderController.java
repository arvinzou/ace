package com.huacainfo.ace.jxb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Order;
import com.huacainfo.ace.jxb.service.OrderService;
import com.huacainfo.ace.jxb.vo.OrderVo;
import com.huacainfo.ace.jxb.vo.OrderQVo;

@Controller
@RequestMapping("/order")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(订单)
 */
public class OrderController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderService orderService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(订单分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<OrderVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findOrderList")
	@ResponseBody
	public PageResult<OrderVo> findOrderList(OrderQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<OrderVo> rst = this.orderService
				.findOrderList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertOrder
	    * @Description:  TODO(添加订单)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertOrder")
	@ResponseBody
	public MessageResponse insertOrder(String jsons) throws Exception {
		Order obj = JSON.parseObject(jsons, Order.class);
		return this.orderService
				.insertOrder(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateOrder
	    * @Description:  TODO(更新订单)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateOrder")
	@ResponseBody
	public MessageResponse updateOrder(String jsons) throws Exception {
		Order obj = JSON.parseObject(jsons, Order.class);
		return this.orderService
				.updateOrder(obj, this.getCurUserProp());
	}
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
	@RequestMapping(value = "/selectOrderByPrimaryKey")
	@ResponseBody
	public SingleResult<OrderVo> selectOrderByPrimaryKey(String id)
			throws Exception {
		return this.orderService.selectOrderByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteOrderByOrderId
	    * @Description:  TODO(删除订单)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteOrderByOrderId")
	@ResponseBody
	public MessageResponse deleteOrderByOrderId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.orderService.deleteOrderByOrderId(id,
				this.getCurUserProp());
	}
}
