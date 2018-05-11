package com.huacainfo.ace.fop.web.controller;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.dao.FopLawPaperDao;
import com.huacainfo.ace.fop.model.FopFinanceProject;
import com.huacainfo.ace.fop.model.FopGeHelp;
import com.huacainfo.ace.fop.model.FopLoanProduct;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.*;
import com.huacainfo.ace.portal.service.UsersService;
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

    @Autowired
    private FopGeHelpService fopGeHelpService;

    @Autowired
    private FopQuestionService fopQuestionService;


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
     * <p>
     * page  页码
     * limit 每页数目
     * title 搜索关键字
     * sord  排序 null：降序，asc：升序
     * noticeType  信息类型 1、市场信息 2、产品信息 3、项目信息 4 、招商信息
     * return rst;
     *
     * @throws Exception
     */
    @RequestMapping(value = "/findNoticeList")
    @ResponseBody
    public ResultResponse findFopNoticeList(FopNoticeQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        return this.fopNoticeService.findNoticeList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
    }


    /**
     * @param: @param id
     */
    @RequestMapping(value = "/selectNoticeByPrimaryKey")
    @ResponseBody
    public ResultResponse selectFopNoticeByPrimaryKey(String id)
            throws Exception {
        return this.fopNoticeService.selectNoticeByPrimaryKey(id);
    }


    /**
     * 查询公告列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homepageNoticeList")
    @ResponseBody
    public ResultResponse homepageNoticeList() throws Exception {
        ResultResponse rst = this.fopNoticeService.homepageNoticeList();
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
     * financeTitle 融资名称
     * financeYear  融资年限
     * btmYield     起始收益率
     * topYield     截至收益率
     *
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
     * financeTitle   融资名称
     * financeAmount  融资金额
     * financeYear    融资年限
     * financeContent 融资内容
     * yearYield      融资年收益
     */
    @RequestMapping(value = "/insertFinanceProject")
    @ResponseBody
    public MessageResponse insertFopFinanceProject(String jsons) throws Exception {
        FopFinanceProject obj = JSON.parseObject(jsons, FopFinanceProject.class);
        return this.fopFinanceProjectService.insertFopFinanceProject(obj, this.getCurUserProp());
    }

    /**
     * productName 产品名称
     * suretyType  担保方式
     * btmRate     最低低利率
     * topRate     最高低利率
     * btmAmount   最低金额
     * topAmount   最高金额
     * loanYear    贷款年限
     * page        页码
     * limit       每页条目数
     *
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
     * productName 产品名称
     * loanAmount  贷款额度
     * loanType    贷款用途
     * suretyType  担保方式
     * loanYear    贷款年限
     * loanYear    贷款年限
     * loanRate    贷款年利率
     * description 产品描述
     */
    @RequestMapping(value = "/insertLoanProduct")
    @ResponseBody
    public MessageResponse insertFopLoanProduct(String jsons) throws Exception {
        FopLoanProduct obj = JSON.parseObject(jsons, FopLoanProduct.class);
        return this.fopLoanProductService
                .insertFopLoanProduct(obj, this.getCurUserProp());
    }


    /**
     * @param: @param id
     */
    @RequestMapping(value = "/selectLoanProductByPrimaryKey")
    @ResponseBody
    public SingleResult<FopLoanProductVo> selectFopLoanProductByPrimaryKey(String id)
            throws Exception {
        return this.fopLoanProductService.selectFopLoanProductByPrimaryKey(id);
    }


    /**
     * 获取政企服务列表
     *
     * @param condition title  (标题)
     *                  sord  (默认:降序，asc：升序)
     *                  replied  (true:回复，false：未回复）
     *                  status (1:未发布，2：已发布)
     *                  page:页数
     *                  limit：每页目数
     * @param page
     */
    @RequestMapping(value = "/findGeHelpList")
    @ResponseBody
    public ResultResponse findGeHelpList(FopGeHelpQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        ResultResponse rst = this.fopGeHelpService.findGeHelpList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }


    /**
     * 发布政企诉求
     *
     * @param: fg
     * title:标题
     * content:内容
     */
    @RequestMapping(value = "/insertGeHelp")
    @ResponseBody
    public MessageResponse insertGeHelp(FopGeHelp fg) throws Exception {
        return this.fopGeHelpService.insertGeHelp(fg, this.getCurUserProp());
    }

    /**
     * 修改政企诉求
     *
     * @param fg id:政企诉求id
     *           title：题目
     *           content：内容
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateGeHelp")
    @ResponseBody
    public MessageResponse updateFopGeHelp(FopGeHelp fg) throws Exception {
        return this.fopGeHelpService.updateFopGeHelp(fg, this.getCurUserProp());
    }

    /**
     * 查看诉求详细
     * id :政企诉求
     */
    @RequestMapping(value = "/selectGeHelpByPrimaryKey")
    @ResponseBody
    public ResultResponse selectFopGeHelpByPrimaryKey(String id) throws Exception {
        return this.fopGeHelpService.selectGeHelpByPrimaryKey(id);
    }


    /**
     * fullName：标题
     * areaCode：所属区域
     * companyProperty：公司性质（见portal.dic_134）
     * page:页码
     * limit：每页目数
     */
    @RequestMapping(value = "/findCompanyList")
    @ResponseBody
    public ResultResponse findCompanyList(FopCompanyQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.fopCompanyService.findCompanyList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }


    /**
     * id 企业ID
     */
    @RequestMapping(value = "/selectCompanyByPrimaryKey")
    @ResponseBody
    public ResultResponse selectCompanyByPrimaryKey(String id)
            throws Exception {
        return this.fopCompanyService.selectCompanyByPrimaryKey(id);
    }


    @RequestMapping(value = "/findQuestionList")
    @ResponseBody
    public PageResult<FopQuestionVo> findQuestionList(FopQuestionQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult
                <FopQuestionVo> rst = this.fopQuestionService.findFopQuestionList(condition, page.getStart(), page.getLimit(),
                page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }
}
