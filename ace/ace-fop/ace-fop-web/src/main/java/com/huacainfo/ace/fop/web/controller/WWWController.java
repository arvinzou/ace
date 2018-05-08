package com.huacainfo.ace.fop.web.controller;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.FopLawPaperDao;
import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/www")
public class WWWController extends FopBaseController {
    @Autowired
    private FopCompanyService fopCompanyService;

    @Autowired
    private FopNoticeService fopNoticeService;

    @Autowired
    private FopLawPaperService fopLawPaperService;

    @Autowired
    private FopFinanceProjectService fopFinanceProjectService;

    @Autowired
    private FopLoanProductService fopLoanProductService;

    /**
     * gis地图
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/companyGis")
    @ResponseBody
    public PageResult<FopCompanyVo> findFopCompanyList(FopCompanyQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopCompanyVo> rst = this.fopCompanyService
                .findFopCompanyList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * 查询公告列表
     *
     * @param page  页码
     * @param limit 每页数目
     * @param title 搜索关键字
     * @param sort  排序 null：降序，不为null：升序
     * @param noticeType  信息类型 1、市场信息 2、产品信息 3、项目信息 4 、招商信息
     * @return return rst;
     *
     * @throws Exception
     */
    @RequestMapping(value = "/findNoticeList")
    @ResponseBody
    public PageResult<FopNoticeVo> findFopNoticeList(FopNoticeQVo condition, int page, int limit, String sort) throws Exception {
        PageResult<FopNoticeVo> rst = this.fopNoticeService.findNoticeList(condition, sort, page, limit);
        return rst;
    }


    /**
     * @param: @param id
     */
    @RequestMapping(value = "/selectNoticeByPrimaryKey")
    @ResponseBody
    public SingleResult<FopNoticeVo> selectFopNoticeByPrimaryKey(String id)
            throws Exception {
        return this.fopNoticeService.selectFopNoticeByPrimaryKey(id);
    }


    /**
     * 查询公告列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homepageNoticeList")
    @ResponseBody
    public PageResult<FopNoticeVo> homepageNoticeList() throws Exception {
        PageResult<FopNoticeVo> rst = this.fopNoticeService.homepageNoticeList();
        return rst;
    }

    /**
     * @param: @param keyWord:搜索关键字
     * @param: @param page ：页码
     * @param: @param limit ：每页数目
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<FopLawPaperVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/findLawPaperList")
    @ResponseBody
    public PageResult<FopLawPaperVo> findLawPaperList(String keyWord, int page, int limit) throws Exception {
        PageResult<FopLawPaperVo> rst = this.fopLawPaperService.findLawPaperList(keyWord, page, limit);
        return rst;
    }

    /**
     * @param financeTitle 融资名称
     * @param financeYear  融资年限
     * @param btmYield     起始收益率
     * @param topYield     截至收益率
     * @param: page ：页码
     * @param: limit ：每页数目
     */

    @RequestMapping(value = "/findFinanceProjectList")
    @ResponseBody
    public PageResult<FopFinanceProjectVo>
    findFopFinanceProjectList(FopFinanceProjectQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopFinanceProjectVo> rst = this.fopFinanceProjectService.findFinanceProjectList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    /**
     * @param id 融资ID
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/selectFinanceProjectByPrimaryKey")
    @ResponseBody
    public SingleResult<FopFinanceProjectVo> selectFopFinanceProjectByPrimaryKey(String id)
            throws Exception {
        return this.fopFinanceProjectService.selectFopFinanceProjectByPrimaryKey(id);
    }


    /**
     * @param financeTitle   融资名称
     * @param financeAmount  融资金额
     * @param financeYear    融资年限
     * @param financeContent 融资内容
     * @param yearYield      融资年收益
     */
    @RequestMapping(value = "/insertFinanceProject")
    @ResponseBody
    public MessageResponse insertFopFinanceProject(String jsons) throws Exception {
        FopFinanceProject obj = JSON.parseObject(jsons, FopFinanceProject.class);
        return this.fopFinanceProjectService.insertFopFinanceProject(obj, this.getCurUserProp());
    }

    /**
     * @param productName 产品名称
     * @param suretyType  担保方式
     * @param btmRate     最低低利率
     * @param topRate     最高低利率
     * @param btmAmount   最低金额
     * @param topAmount   最高金额
     * @param loanYear    贷款年限
     * @param page        页码
     * @param limit       每页条目数
     * @return
     * @throws Exception
     */


    @RequestMapping(value = "/findLoanProductList")
    @ResponseBody
    public PageResult<FopLoanProductVo> findFopLoanProductList(FopLoanProductQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopLoanProductVo> rst = this.fopLoanProductService
                .findFopLoanProductList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * @param productName 产品名称
     * @param loanAmount  贷款额度
     * @param loanType    贷款用途
     * @param suretyType  担保方式
     * @param loanYear    贷款年限
     * @param loanYear    贷款年限
     * @param loanRate    贷款年利率
     * @param description 产品描述
     */
    @RequestMapping(value = "/insertLoanProduct")
    @ResponseBody
    public MessageResponse insertFopLoanProduct(String jsons) throws Exception {
        FopLoanProduct obj = JSON.parseObject(jsons, FopLoanProduct.class);
        return this.fopLoanProductService
                .insertFopLoanProduct(obj, this.getCurUserProp());
    }

}
