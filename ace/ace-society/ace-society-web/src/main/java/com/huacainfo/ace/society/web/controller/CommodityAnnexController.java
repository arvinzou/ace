package com.huacainfo.ace.society.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.CommodityAnnex;
import com.huacainfo.ace.society.service.CommodityAnnexService;
import com.huacainfo.ace.society.vo.CommodityAnnexQVo;
import com.huacainfo.ace.society.vo.CommodityAnnexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/commodityAnnex")
/**
 * @author: Arvin
 * @version: 2018-09-13
 * @Description: TODO(爱心商品附录)
 */
public class CommodityAnnexController extends SocietyBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommodityAnnexService commodityAnnexService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(爱心商品附录分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <CommodityAnnexVo>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/findCommodityAnnexList")
    @ResponseBody
    public PageResult
            <CommodityAnnexVo> findCommodityAnnexList(CommodityAnnexQVo condition,
                                                      PageParamNoChangeSord page) throws Exception {

        PageResult
                <CommodityAnnexVo> rst = this.commodityAnnexService
                .findCommodityAnnexList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCommodityAnnex
     * @Description: TODO(添加爱心商品附录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/insertCommodityAnnex")
    @ResponseBody
    public MessageResponse insertCommodityAnnex(String jsons) throws Exception {
        CommodityAnnex obj = JSON.parseObject(jsons, CommodityAnnex.class);
        return this.commodityAnnexService.insertCommodityAnnex(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCommodityAnnex
     * @Description: TODO(更新爱心商品附录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/updateCommodityAnnex")
    @ResponseBody
    public MessageResponse updateCommodityAnnex(String jsons) throws Exception {
        CommodityAnnex obj = JSON.parseObject(jsons, CommodityAnnex.class);
        return this.commodityAnnexService.updateCommodityAnnex(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCommodityAnnexByPrimaryKey
     * @Description: TODO(获取爱心商品附录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CommodityAnnex>
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/selectCommodityAnnexByPrimaryKey")
    @ResponseBody
    public SingleResult
            <CommodityAnnexVo> selectCommodityAnnexByPrimaryKey(String id) throws Exception {
        return this.commodityAnnexService.selectCommodityAnnexByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCommodityAnnexByCommodityAnnexId
     * @Description: TODO(删除爱心商品附录)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-09-13
     */
    @RequestMapping(value = "/deleteCommodityAnnexByCommodityAnnexId")
    @ResponseBody
    public MessageResponse deleteCommodityAnnexByCommodityAnnexId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.commodityAnnexService.deleteCommodityAnnexByCommodityAnnexId(id, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核爱心商品附录)
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

        return this.commodityAnnexService.audit(id, rst, message, this.getCurUserProp());
    }
}
