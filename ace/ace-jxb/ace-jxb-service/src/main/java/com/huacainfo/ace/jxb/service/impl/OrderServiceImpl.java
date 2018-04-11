package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.jxb.dao.OrderDao;
import com.huacainfo.ace.jxb.model.Order;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.OrderService;
import com.huacainfo.ace.jxb.vo.OrderVo;
import com.huacainfo.ace.jxb.vo.OrderQVo;
@Service("orderService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(订单)
 */
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

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
    @Override
	public PageResult<OrderVo> findOrderList(OrderQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<OrderVo> rst = new PageResult<OrderVo>();
		List<OrderVo> list = this.orderDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.orderDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertOrder
	    * @Description:  TODO(添加订单)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertOrder(Order o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getConsumerId())) {
return new MessageResponse(1, "客户主键不能为空！");
}
if (CommonUtils.isBlank(o.getCommodityId())) {
return new MessageResponse(1, "商品主键不能为空！");
}
if (CommonUtils.isBlank(o.getCategory())) {
return new MessageResponse(1, "订单类型不能为空！");
}
if (CommonUtils.isBlank(o.getCommodityName())) {
return new MessageResponse(1, "商品名称不能为空！");
}
if (CommonUtils.isBlank(o.getBusinessName())) {
return new MessageResponse(1, "商家名称不能为空！");
}
if (CommonUtils.isBlank(o.getAmount())) {
return new MessageResponse(1, "数量不能为空！");
}
		int temp = this.orderDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "订单名称重复！");
		}
		o.setCreateDate(new Date());
		o.setPayStatus("1");
		//o.setCreateUserName(userProp.getName());
		o.setConsumerId(userProp.getUserId());
		this.orderDao.insert(o);
		this.dataBaseLogService.log("添加订单", "订单", "", o.getCommodityName(),
				o.getCommodityName(), userProp);
		return new MessageResponse(0, "添加订单完成！");
	}
    /**
	 *
	    * @Title:updateOrder
	    * @Description:  TODO(更新订单)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateOrder(Order o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getConsumerId())) {
return new MessageResponse(1, "客户主键不能为空！");
}
if (CommonUtils.isBlank(o.getCommodityId())) {
return new MessageResponse(1, "商品主键不能为空！");
}
if (CommonUtils.isBlank(o.getCategory())) {
return new MessageResponse(1, "订单类型不能为空！");
}
if (CommonUtils.isBlank(o.getCommodityName())) {
return new MessageResponse(1, "商品名称不能为空！");
}
if (CommonUtils.isBlank(o.getBusinessName())) {
return new MessageResponse(1, "商家名称不能为空！");
}
if (CommonUtils.isBlank(o.getAmount())) {
return new MessageResponse(1, "数量不能为空！");
}
		
		o.setPayTime(new Date());
		//o.setLastModifyUserName(userProp.getName());
		o.setConsumerId(userProp.getUserId());
		this.orderDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更订单", "订单", "", o.getCommodityName(),
				o.getCommodityName(), userProp);
		return new MessageResponse(0, "变更订单完成！");
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
    @Override
	public SingleResult<OrderVo> selectOrderByPrimaryKey(String id) throws Exception {
		SingleResult<OrderVo> rst = new SingleResult<OrderVo>();
		rst.setValue(this.orderDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteOrderByOrderId
	    * @Description:  TODO(删除订单)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteOrderByOrderId(String id,
			UserProp userProp) throws Exception {
		this.orderDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除订单", "订单", String.valueOf(id),
				String.valueOf(id), "订单", userProp);
		return new MessageResponse(0, "订单删除完成！");
	}
}
