package com.huacainfo.ace.fop.web.controller;

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
import com.huacainfo.ace.fop.model.InvestmentInfo;
import com.huacainfo.ace.fop.service.InvestmentInfoService;
import com.huacainfo.ace.fop.vo.InvestmentInfoVo;
import com.huacainfo.ace.fop.vo.InvestmentInfoQVo;

@Controller
@RequestMapping("/investmentInfo")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(招商信息)
 */
public class InvestmentInfoController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InvestmentInfoService investmentInfoService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(招商信息分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <InvestmentInfoVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findInvestmentInfoList")
    @ResponseBody
    public PageResult
            <InvestmentInfoVo> findInvestmentInfoList(InvestmentInfoQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <InvestmentInfoVo> rst = this.investmentInfoService
                .findInvestmentInfoList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertInvestmentInfo
     * @Description: TODO(添加招商信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertInvestmentInfo")
    @ResponseBody
    public MessageResponse insertInvestmentInfo(String jsons) throws Exception {
        InvestmentInfo obj = JSON.parseObject(jsons, InvestmentInfo.class);
        return this.investmentInfoService.insertInvestmentInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateInvestmentInfo
     * @Description: TODO(更新招商信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateInvestmentInfo")
    @ResponseBody
    public MessageResponse updateInvestmentInfo(String jsons) throws Exception {
        InvestmentInfo obj = JSON.parseObject(jsons, InvestmentInfo.class);
        return this.investmentInfoService.updateInvestmentInfo(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectInvestmentInfoByPrimaryKey
     * @Description: TODO(获取招商信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InvestmentInfo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectInvestmentInfoByPrimaryKey")
    @ResponseBody
    public SingleResult
            <InvestmentInfoVo> selectInvestmentInfoByPrimaryKey(String id) throws Exception {
        return this.investmentInfoService.selectInvestmentInfoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteInvestmentInfoByInvestmentInfoId
     * @Description: TODO(删除招商信息)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteInvestmentInfoByInvestmentInfoId")
    @ResponseBody
    public MessageResponse deleteInvestmentInfoByInvestmentInfoId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.investmentInfoService.deleteInvestmentInfoByInvestmentInfoId(id, this.getCurUserProp());
    }
}
