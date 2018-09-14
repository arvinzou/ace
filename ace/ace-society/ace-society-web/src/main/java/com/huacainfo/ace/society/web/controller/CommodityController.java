package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Commodity;
import com.huacainfo.ace.society.service.CommodityService;
import com.huacainfo.ace.society.vo.CommodityQVo;
import com.huacainfo.ace.society.vo.CommodityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commodity")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品)
 */
public class CommodityController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommodityService commodityService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/findCommodityList")
    @ResponseBody
    public PageResult<CommodityVo> findCommodityList(CommodityQVo condition,
                                                     PageParamNoChangeSord page) throws Exception {
        //默认只查询有效商品数据
        condition.setStatus(StringUtil.isEmpty(condition.getStatus()) ? "1" : condition.getStatus());

        PageResult<CommodityVo> rst = this.commodityService
                .findCommodityList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCommodity
     * @Description: TODO(添加爱心商品)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/insertCommodity")
    @ResponseBody
    public MessageResponse insertCommodity(String jsons) throws Exception {
        Commodity obj = JSON.parseObject(jsons, Commodity.class);
        return this.commodityService.insertCommodity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCommodity
     * @Description: TODO(更新爱心商品)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/updateCommodity")
    @ResponseBody
    public MessageResponse updateCommodity(String jsons) throws Exception {
        Commodity obj = JSON.parseObject(jsons, Commodity.class);
        return this.commodityService.updateCommodity(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCommodityByPrimaryKey
     * @Description: TODO(获取爱心商品)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Commodity>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/selectCommodityByPrimaryKey")
    @ResponseBody
    public SingleResult<CommodityVo> selectCommodityByPrimaryKey(String id) throws Exception {
        return this.commodityService.selectCommodityByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCommodityByCommodityId
     * @Description: TODO(删除爱心商品)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/deleteCommodityByCommodityId")
    @ResponseBody
    public MessageResponse deleteCommodityByCommodityId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.commodityService.deleteCommodityByCommodityId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param message 审核说明
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String rst, String message) throws Exception {

        return this.commodityService.audit(id, rst, message, this.getCurUserProp());
    }
}
