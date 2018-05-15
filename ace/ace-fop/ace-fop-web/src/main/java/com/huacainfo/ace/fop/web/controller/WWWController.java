package com.huacainfo.ace.fop.web.controller;


import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private FopAppealHelpService fopAppealHelpService;

    @Autowired
    private FopProjectService fopProjectService;

    @Autowired
    private FopAssociationService fopAssociationService;

    @Autowired
    private FopQuestionnaireResultService fopQuestionnaireResultService;


//    /**
//     * gis地图
//     *
//     * @param condition
//     * @param page
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/companyGis")
//    @ResponseBody
//    public PageResult<FopCompanyVo> findCompanyList(FopCompanyQVo condition, PageParamNoChangeSord page) throws Exception {
//        PageResult<FopCompanyVo> rst = this.fopCompanyService
//                .findFopCompanyList(condition, page.getStart(), page.getLimit(),
//                        page.getOrderBy());
//        if (rst.getTotal() == 0) {
//            rst.setTotal(page.getTotalRecord());
//        }
//        return rst;
//    }


    /**
     * 查询公告列表
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
    public ResultResponse findNoticeList(FopNoticeQVo condition, PageParamNoChangeSord page) throws Exception {
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
    public ResultResponse selectNoticeByPrimaryKey(String id)
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
    public ResultResponse findLawPaperList(FopLawPaperQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.fopLawPaperService.findLawPaperList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }


    /**
     * 获取法律文书
     *
     * @param id;
     */

    @RequestMapping(value = "/selectLawPaperByPrimaryKey")
    @ResponseBody
    public ResultResponse selectFopLawPaperByPrimaryKey(String id)
            throws Exception {
        return this.fopLawPaperService.selectLawPaperByPrimaryKey(id);
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
    public ResultResponse findFinanceProjectList(FopFinanceProjectQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.fopFinanceProjectService.findFinanceProjectList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * @param id 融资ID
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/selectFinanceProjectByPrimaryKey")
    @ResponseBody
    public ResultResponse selectFinanceProjectByPrimaryKey(String id)
            throws Exception {
        return this.fopFinanceProjectService.selectFinanceProjectByPrimaryKey(id);
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
    public MessageResponse insertFinanceProject(FopFinanceProject obj) throws Exception {
        return this.fopFinanceProjectService.insertFinanceProject(obj, this.getCurUserProp());
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
    public ResultResponse findLoanProductList(FopLoanProductQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.fopLoanProductService.findLoanProductList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
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
    public MessageResponse insertLoanProduct(FopLoanProduct obj) throws Exception {
        return this.fopLoanProductService
                .insertFopLoanProduct(obj, this.getCurUserProp());
    }


    /**
     * @param: @param id
     */
    @RequestMapping(value = "/selectLoanProductByPrimaryKey")
    @ResponseBody
    public ResultResponse selectLoanProductByPrimaryKey(String id)
            throws Exception {
        return this.fopLoanProductService.selectLoanProductByPrimaryKey(id);
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
    public MessageResponse updateGeHelp(FopGeHelp fg) throws Exception {
        return this.fopGeHelpService.updateFopGeHelp(fg, this.getCurUserProp());
    }

    /**
     * 查看诉求详细
     * id :政企诉求
     */
    @RequestMapping(value = "/selectGeHelpByPrimaryKey")
    @ResponseBody
    public ResultResponse selectGeHelpByPrimaryKey(String id) throws Exception {
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
     * 查看企业详情
     * id 企业ID
     */
    @RequestMapping(value = "/selectCompanyByPrimaryKey")
    @ResponseBody
    public ResultResponse selectCompanyByPrimaryKey(String id)
            throws Exception {
        return this.fopCompanyService.selectCompanyByPrimaryKey(id);
    }

    /**
     * 获取法律帮助列表
     */

    @RequestMapping(value = "/findLawQuestionList")
    @ResponseBody
    public ResultResponse findLawQuestionList(FopQuestionQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        ResultResponse rst = this.fopQuestionService.findQuestionList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * 发布法律帮助
     * title：标题
     * subType：类型
     * content：内容
     */

    @RequestMapping(value = "/insertLawQuestion")
    @ResponseBody
    public MessageResponse insertFopQuestion(FopQuestion obj) throws Exception {
        return this.fopQuestionService.insertLawQuestion(obj, this.getCurUserProp());
    }

    /**
     * 获取评论列表
     */
    @RequestMapping(value = "/findCommentList")
    @ResponseBody
    public ResultResponse findCommentList(FopQuestionQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.fopQuestionService.findQuestionList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }


    /**
     * 获取question 详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectQuestionByPrimaryKey")
    @ResponseBody
    public ResultResponse selectFopQuestionByPrimaryKey(String id)
            throws Exception {
        return this.fopQuestionService.selectQuestionByPrimaryKey(id);
    }


    /**
     * 插入评论
     * parentId：被评论的id
     * sourceType:0-法律帮助Q&A, 1-政府诉求Q&A,
     * 2-融资项目评论&留言, 3-融资产品评论&留言,4-项目评论&留言
     * reply：回复内容.
     */

    @RequestMapping(value = "/insertQuestion")
    @ResponseBody
    public MessageResponse insertQuestion(FopQuestion obj) throws Exception {
        return this.fopQuestionService.insertQuestion(obj, this.getCurUserProp());
    }


    /**
     * 发布诉求
     * requestTitle 标题
     * replied  (true:回复，false：未回复）
     * sord  (默认:降序，asc：升序)
     * page：页码
     * limit 每页目数
     */
    @RequestMapping(value = "/findAppealHelpList")
    @ResponseBody
    public ResultResponse findFopAppealHelpList(FopAppealHelpQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("submitDate");
        }
        ResultResponse rst = this.fopAppealHelpService.findAppealHelpList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * 获取诉求服务详情。
     * id 诉求ID
     */
    @RequestMapping(value = "/selectAppealHelpByPrimaryKey")
    @ResponseBody
    public ResultResponse selectAppealHelpByPrimaryKey(String id) throws Exception {
        return this.fopAppealHelpService.selectAppealHelpByPrimaryKey(id);
    }

    /**
     * 发布诉求
     * requestTitle 标题
     * requestDesc 内容
     */

    @RequestMapping(value = "/insertAppealHelp")
    @ResponseBody
    public MessageResponse insertAppealHelp(FopAppealHelp obj) throws Exception {
        return this.fopAppealHelpService.insertFopAppealHelp(obj, this.getCurUserProp());
    }


    /**
     * 查找所有项目
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findProjectList")
    @ResponseBody
    public ResultResponse findProjectList(FopProjectQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        ResultResponse rst = this.fopProjectService.findProjectList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * 发布项目
     * projectName:项目名称
     * coopType：合作方式 1、投资合作，2、合作开发，3、出资+资源合作，4、其他
     * areaCode：所属区域
     * projectType:项目类型。
     * coopDesc：内容
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertProject")
    @ResponseBody
    public MessageResponse insertProject(FopProject obj) throws Exception {
        return this.fopProjectService.insertProject(obj, this.getCurUserProp());
    }


    /**
     * 查看项目详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectProjectByPrimaryKey")
    @ResponseBody
    public ResultResponse selectProjectByPrimaryKey(String id) throws Exception {
        return this.fopProjectService.selectProjectByPrimaryKey(id);
    }

    /**
     * projectName:项目名称
     * coopType：合作方式 1、投资合作，2、合作开发，3、出资+资源合作，4、其他
     * areaCode：所属区域
     * projectType:项目类型。
     * coopDesc：内容
     * @throws Exception
     */
    @RequestMapping(value = "/updateProject")
    @ResponseBody
    public MessageResponse updateProject(FopProject obj) throws Exception {
        return this.fopProjectService.updateFopProject(obj, this.getCurUserProp());
    }


    /**
     * name：企业，团体名称
     * phoneNumber：电话
     * isCompany :true, false。
     *
     * @return
     * @throws Exception
     */

//    @RequestMapping(value = "/sign")
//    @ResponseBody
//    public MessageResponse signUp(String name,String phoneNumber,boolean isCompany) throws Exception {
//        if(isCompany){
//            return this.fopCompanyService.insertCompany(name,phoneNumber);
//        }
//        return this.fopAssociationService.insertAssociation(name,phoneNumber);
//    }


    /**
     * 满意度列表
     * opinionType：1、诉求满意度 2、合作交流
     */
    @RequestMapping(value = "/findQuestionnaireResultList")
    @ResponseBody
    public ResultResponse findFopQuestionnaireResultList(FopQuestionnaireResultQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        ResultResponse rst = this.fopQuestionnaireResultService
                .findQuestionnaireResultList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * opinionType 意见类型
     * result 调查结果 1、很满意，2、一半，3、不满意
     * content 内容
     */

    @RequestMapping(value = "/insertQuestionnaireResult")
    @ResponseBody
    public MessageResponse insertQuestionnaireResult(FopQuestionnaireResultQVo condition) throws Exception {
        return this.fopQuestionnaireResultService.insertQuestionnaireResult(condition, this.getCurUserProp());
    }

    /**
     * 统计数据
     */
    @RequestMapping(value = "/statisticalData")
    @ResponseBody
    public ResultResponse statisticalData() throws Exception {
        return this.fopQuestionnaireResultService.statisticalData();
    }
}
