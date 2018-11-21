package com.huacainfo.ace.jxb.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.service.BaseOrderService;
import com.huacainfo.ace.jxb.vo.BaseOrderQVo;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baseOrder")
/**
 * @author: Arvin
 * @version: 2018-07-30
 * @Description: TODO(基础订单接口)
 */
public class BaseOrderController extends JxbBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseOrderService baseOrderService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(基础订单接口分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <BaseOrderVo>
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/findBaseOrderList")
    @ResponseBody
    public PageResult<BaseOrderVo> findBaseOrderList(BaseOrderQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<BaseOrderVo> rst = this.baseOrderService.findBaseOrderList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/findBaseOrderListSecond")
    @ResponseBody
    public ResultResponse findBaseOrderListSecond(BaseOrderQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.baseOrderService.findBaseOrderListSencond(condition, page.getPage(), page.getLimit(), page.getOrderBy());
    }


    @RequestMapping(value = "/findMyOrderList")
    @ResponseBody
    public ResultResponse findMyOrderList(BaseOrderQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setBusinessId(this.getCurUserProp().getUserId());
        return this.baseOrderService.findBaseOrderListSencond(condition, page.getPage(), page.getLimit(), page.getOrderBy());
    }


    /**
     * @throws
     * @Title:insertBaseOrder
     * @Description: TODO(添加基础订单接口)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/insertBaseOrder")
    @ResponseBody
    public MessageResponse insertBaseOrder(String jsons) throws Exception {
        BaseOrder obj = JSON.parseObject(jsons, BaseOrder.class);
        return this.baseOrderService.insertBaseOrder(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateBaseOrder
     * @Description: TODO(更新基础订单接口)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/updateBaseOrder")
    @ResponseBody
    public MessageResponse updateBaseOrder(String jsons) throws Exception {
        BaseOrder obj = JSON.parseObject(jsons, BaseOrder.class);
        return this.baseOrderService.updateBaseOrder(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取基础订单接口)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/selectBaseOrderByPrimaryKey")
    @ResponseBody
    public SingleResult<BaseOrderVo> selectBaseOrderByPrimaryKey(String id) throws Exception {
        return this.baseOrderService.selectBaseOrderByPrimaryKey(id);
    }


    /**
     * @throws
     * @Title:selectBaseOrderByPrimaryKey
     * @Description: TODO(获取基础订单接口)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<BaseOrder>
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/orderInfo")
    @ResponseBody
    public ResultResponse orderInfoByPrimaryKey(String id) throws Exception {
        return this.baseOrderService.orderInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteBaseOrderByBaseOrderId
     * @Description: TODO(删除基础订单接口)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-30
     */
    @RequestMapping(value = "/deleteBaseOrderByBaseOrderId")
    @ResponseBody
    public MessageResponse deleteBaseOrderByBaseOrderId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.baseOrderService.deleteBaseOrderByBaseOrderId(id, this.getCurUserProp());
    }


}
