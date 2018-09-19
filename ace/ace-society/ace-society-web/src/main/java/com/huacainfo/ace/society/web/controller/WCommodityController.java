package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.service.CommodityService;
import com.huacainfo.ace.society.vo.CommodityQVo;
import com.huacainfo.ace.society.vo.CommodityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Arvin
 * @Date: 2018/9/19 11:04
 * @Description:
 */
@RestController
@RequestMapping("/www/commodity")
public class WCommodityController extends SocietyBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommodityService commodityService;


    /***
     * 获取商品列表 - 包含场地&商品
     * @return ResultResponse ( PageResult<CommodityVo> )
     */
    @RequestMapping("/getList")
    public ResultResponse getList(CommodityQVo condition, PageParamNoChangeSord page) throws Exception {
        //默认只查询有效商品数据
        condition.setStatus(StringUtil.isEmpty(condition.getStatus()) ? "1" : condition.getStatus());

        PageResult<CommodityVo> rst = commodityService.findCommodityList(condition,
                page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", rst);
    }

    /**
     * 获取商品详情
     *
     * @param id society.commodity.id
     * @return ResultResponse
     */
    @RequestMapping("/getList")
    public ResultResponse getDetail(String id) throws Exception {
        CommodityVo vo = commodityService.selectCommodityByPrimaryKey(id).getValue();

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", vo);
    }

}
