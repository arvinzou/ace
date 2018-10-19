package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.OrderInfo;
import com.huacainfo.ace.society.service.OrderInfoService;
import com.huacainfo.ace.society.vo.OrderInfoQVo;
import com.huacainfo.ace.society.vo.OrderInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderInfo")
/**
 * @author: Arvin
 * @version: 2018-09-17
 * @Description: TODO(订单管理)
 */
public class OrderInfoController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(订单管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <OrderInfoVo>
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/findOrderInfoList")
    @ResponseBody
    public PageResult<OrderInfoVo> findOrderInfoList(OrderInfoQVo condition,
                                                     PageParamNoChangeSord page) throws Exception {

        PageResult<OrderInfoVo> rst =
                orderInfoService.findOrderInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertOrderInfo
     * @Description: TODO(添加订单管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/insertOrderInfo")
    @ResponseBody
    public MessageResponse insertOrderInfo(String jsons) throws Exception {
        OrderInfo obj = JSON.parseObject(jsons, OrderInfo.class);
        return this.orderInfoService.insertOrderInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateOrderInfo
     * @Description: TODO(更新订单管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/updateOrderInfo")
    @ResponseBody
    public MessageResponse updateOrderInfo(String jsons) throws Exception {
        OrderInfo obj = JSON.parseObject(jsons, OrderInfo.class);
        return this.orderInfoService.updateOrderInfo(obj, this.getCurUserProp());
    }

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
    @RequestMapping(value = "/selectOrderInfoByPrimaryKey")
    @ResponseBody
    public SingleResult<OrderInfoVo> selectOrderInfoByPrimaryKey(String id) throws Exception {
        return this.orderInfoService.selectOrderInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteOrderInfoByOrderInfoId
     * @Description: TODO(删除订单管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-17
     */
    @RequestMapping(value = "/deleteOrderInfoByOrderInfoId")
    @ResponseBody
    public MessageResponse deleteOrderInfoByOrderInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.orderInfoService.deleteOrderInfoByOrderInfoId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核订单管理)
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

        return this.orderInfoService.audit(id, rst, message, this.getCurUserProp());
    }
}
