package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.OrderDetail;
import com.huacainfo.ace.society.service.OrderDetailService;
import com.huacainfo.ace.society.vo.OrderDetailQVo;
import com.huacainfo.ace.society.vo.OrderDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderDetail")
/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单详情)
 */
public class OrderDetailController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单详情分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <OrderDetailVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/findOrderDetailList")
    @ResponseBody
    public PageResult
            <OrderDetailVo> findOrderDetailList(OrderDetailQVo condition,
                                                PageParamNoChangeSord page) throws Exception {

        PageResult
                <OrderDetailVo> rst = this.orderDetailService
                .findOrderDetailList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertOrderDetail
     * @Description: TODO(添加订单详情)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/insertOrderDetail")
    @ResponseBody
    public MessageResponse insertOrderDetail(String jsons) throws Exception {
        OrderDetail obj = JSON.parseObject(jsons, OrderDetail.class);
        return this.orderDetailService.insertOrderDetail(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateOrderDetail
     * @Description: TODO(更新订单详情)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/updateOrderDetail")
    @ResponseBody
    public MessageResponse updateOrderDetail(String jsons) throws Exception {
        OrderDetail obj = JSON.parseObject(jsons, OrderDetail.class);
        return this.orderDetailService.updateOrderDetail(obj, this.getCurUserProp());
    }

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
    @RequestMapping(value = "/selectOrderDetailByPrimaryKey")
    @ResponseBody
    public SingleResult
            <OrderDetailVo> selectOrderDetailByPrimaryKey(String id) throws Exception {
        return this.orderDetailService.selectOrderDetailByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteOrderDetailByOrderDetailId
     * @Description: TODO(删除订单详情)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/deleteOrderDetailByOrderDetailId")
    @ResponseBody
    public MessageResponse deleteOrderDetailByOrderDetailId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.orderDetailService.deleteOrderDetailByOrderDetailId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单详情)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.orderDetailService.audit(id, rst, message, this.getCurUserProp());
    }
}
