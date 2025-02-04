package com.huacainfo.ace.fop.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.*;
import com.huacainfo.ace.fop.service.*;
import com.huacainfo.ace.fop.vo.*;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

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

    @Autowired
    private InformationServiceService informationServiceService;

    @Autowired
    private RelatedLinksService relatedLinksService;

    @Autowired
    private IntegrityPublicityService integrityPublicityService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FopAssMemberService fopAssMemberService;

    @Autowired
    private FopPersonService fopPersonService;

    @Autowired
    private FopCompanyContributionService fopCompanyContributionService;

    @Autowired
    private FopCompanyOrgService fopCompanyOrgService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    /**
     * gis地图
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/companyGis")
    @ResponseBody
    public ResultResponse findCompanyGisList() throws Exception {
        ResultResponse rst = this.fopCompanyService.findCompanyGisList();
        return rst;
    }


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
    public ResultResponse homepageNoticeList(FopNoticeQVo condition) throws Exception {
        ResultResponse rst = this.fopNoticeService.homepageNoticeList(condition);
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
        ResultResponse rst = this.fopFinanceProjectService.findFinanceProjectList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
     * 修改融资
     * id              融资主键
     * *financeTitle   融资名称
     * financeAmount  融资金额
     * financeYear    融资年限
     * financeContent 融资内容
     * yearYield      融资年收益
     */
    @RequestMapping(value = "/updateFinanceProject")
    @ResponseBody
    public MessageResponse updateFopFinanceProject(FopFinanceProject obj) throws Exception {
        obj.setStatus("1");
        return this.fopFinanceProjectService.updateFopFinanceProject(obj, this.getCurUserProp());
    }

    /**
     * TODO(删除流程记录)
     * id 主键
     */
    @RequestMapping(value = "/deleteFinanceProjectByFopFinanceProjectId")
    @ResponseBody
    public MessageResponse deleteFopFinanceProjectByFopFinanceProjectId(String id)
            throws Exception {
        return this.fopFinanceProjectService.deleteFopFinanceProjectByFopFinanceProjectId(id,
                this.getCurUserProp());
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
        ResultResponse rst = this.fopLoanProductService.findLoanProductList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
     * @throws
     * @Title:deleteFopLoanProductByFopLoanProductId
     * @Description: TODO(删除金融产品)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    @RequestMapping(value = "/deleteLoanProductByFopLoanProductId")
    @ResponseBody
    public MessageResponse deleteFopLoanProductByFopLoanProductId(String id)
            throws Exception {
        return this.fopLoanProductService.deleteFopLoanProductByFopLoanProductId(id,
                this.getCurUserProp());
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
     * @Description: TODO(更新金融产品)
     * id 金融产品主键
     * productName 产品名称
     * loanAmount  贷款额度
     * loanType    贷款用途
     * suretyType  担保方式
     * loanYear    贷款年限
     * loanYear    贷款年限
     * loanRate    贷款年利率
     * description 产品描述
     */
    @RequestMapping(value = "/updateLoanProduct")
    @ResponseBody
    public MessageResponse updateFopLoanProduct(FopLoanProduct obj) throws Exception {
        obj.setStatus("1");
        return this.fopLoanProductService.updateFopLoanProduct(obj, this.getCurUserProp());
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
        ResultResponse rst = this.fopGeHelpService.findGeHelpList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
        fg.setStatus("1");
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
     * @Description: TODO(删除政企服务)
     * id：
     */
    @RequestMapping(value = "/deleteGeHelpByFopGeHelpId")
    @ResponseBody
    public MessageResponse deleteFopGeHelpByFopGeHelpId(String id) throws Exception {
        return this.fopGeHelpService.deleteFopGeHelpByFopGeHelpId(id, this.getCurUserProp());
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
        ResultResponse rst = this.fopQuestionService.findQuestionList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
        ResultResponse rst = this.fopQuestionService.findQuestionList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
     * @Description: TODO(更新法律帮助/政府诉求)
     * id ：法律帮助主键
     * * 发布法律帮助
     * title：标题
     * subType：类型
     * content：内容
     */
    @RequestMapping(value = "/updateQuestion")
    @ResponseBody
    public MessageResponse updateFopQuestion(String jsons) throws Exception {
        FopQuestion obj = JSON.parseObject(jsons, FopQuestion.class);
        obj.setStatus("1");
        return this.fopQuestionService.updateFopQuestion(obj, this.getCurUserProp());
    }


    /**
     * @Description: TODO(删除法律帮助/政府诉求)
     * id ：主键
     */
    @RequestMapping(value = "/deleteQuestionByFopQuestionId")
    @ResponseBody
    public MessageResponse deleteFopQuestionByFopQuestionId(String id)
            throws Exception {
        return this.fopQuestionService.deleteFopQuestionByFopQuestionId(id,
                this.getCurUserProp());
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
        ResultResponse rst = this.fopProjectService.findProjectList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
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
     * id 项目主键
     * projectName:项目名称
     * coopType：合作方式 1、投资合作，2、合作开发，3、出资+资源合作，4、其他
     * areaCode：所属区域
     * projectType:项目类型。
     * coopDesc：内容
     *
     * @throws Exception
     */
    @RequestMapping(value = "/updateProject")
    @ResponseBody
    public MessageResponse updateProject(FopProject obj) throws Exception {
        obj.setStatus("1");
        return this.fopProjectService.updateFopProject(obj, this.getCurUserProp());
    }

    /**
     * id 删除主键
     *
     * @Description: TODO(删除合作项目)
     */
    @RequestMapping(value = "/deleteProjectByFopProjectId")
    @ResponseBody
    public MessageResponse deleteFopProjectByFopProjectId(String id) throws Exception {
        return this.fopProjectService.deleteFopProjectByFopProjectId(id,
                this.getCurUserProp());
    }


    /**
     * name：企业，团体名称
     * phoneNumber：电话
     * isCompany :true, false。
     *
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/sign")
    @ResponseBody
    public MessageResponse signUp(String name, String phoneNumber, String isCompany, String code) throws Exception {
        String rcode = (String) this.getRequest().getSession().getAttribute("j_captcha_cmcc_" + phoneNumber);
        if (StringUtil.isEmpty(code) && code.equals(rcode)) {
            return new MessageResponse(ResultCode.FAIL, "验证码输入有误");
        }
        if (!"2".equals(isCompany)) {
            return this.fopCompanyService.insertCompany(name, isCompany, phoneNumber);
        }
        return this.fopAssociationService.insertAssociation(name, phoneNumber);
    }


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
     * result 调查结果 1、很满意，2、一般，3、不满意
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


    /**
     * 获取信息服务列表
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findInformationServiceListDo")
    @ResponseBody
    public ResultResponse findInformationServiceList(InformationServiceQVo condition, PageParamNoChangeSord page) throws Exception {
        if ("asc".equals(page.getSord())) {
            page.setOrderBy("releaseDate");
        }
        ResultResponse rst = this.informationServiceService.InformationServiceList(condition, page.getPage(), page.getLimit(), page.getOrderBy(), this.getCurUserProp());
        return rst;
    }

    /**
     * 查看信息服务详情
     * id
     */
    @RequestMapping(value = "/selectInformationServiceByPrimaryKeyoDo")
    @ResponseBody
    public ResultResponse selectInformationServiceByPrimaryKey(String id) throws Exception {
        return this.informationServiceService.InformationServiceByPrimaryKey(id);
    }

    /**
     * @Description: TODO(添加信息服务)
     * title
     * content
     * modules
     * fileUrl
     */
    @RequestMapping(value = "/insertInformationServiceDo")
    @ResponseBody
    public MessageResponse insertInformationService(InformationService condition) throws Exception {
        return this.informationServiceService.insertInformationService(condition, this.getCurUserProp());
    }

    /**
     * @Description: TODO(更新信息服务)
     * id
     * title
     * content
     * fileUrl
     */
    @RequestMapping(value = "/updateInformationServiceDo")
    @ResponseBody
    public MessageResponse updateInformationService(InformationService condition) throws Exception {
        condition.setStatus("1");
        return this.informationServiceService.updateInformationService(condition, this.getCurUserProp());
    }

    /**
     * @Description: TODO(删除信息服务)
     * @param: id
     */
    @RequestMapping(value = "/deleteInformationServiceByInformationServiceIdDo")
    @ResponseBody
    public MessageResponse deleteInformationServiceByInformationServiceId(String id) throws Exception {
        return this.informationServiceService.deleteInformationServiceByInformationServiceId(id, this.getCurUserProp());
    }

    /**
     * 统计发布数量
     */
    @RequestMapping(value = "/publishStatistics")
    @ResponseBody
    public ResultResponse publishStatistics() throws Exception {
        return this.informationServiceService.publishStatistics(this.getCurUserProp());
    }

    /**
     * 查询链接树形列表
     */
    @RequestMapping(value = "/relatedLinksTree")
    @ResponseBody
    public ResultResponse relatedLinksTree() throws Exception {
        return new ResultResponse(ResultCode.SUCCESS, "获得成功", this.relatedLinksService.relatedLinksTree("1"));
    }


    /**
     * 获取诚信公告列表
     *
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findIntegrityPublicityListDo")
    @ResponseBody
    public ResultResponse findIntegrityPublicityList(IntegrityPublicityQVo condition, PageParamNoChangeSord page) throws Exception {
        ResultResponse rst = this.integrityPublicityService.findIntegrityPublicityListDo(condition, page.getPage(), page.getLimit(), page.getOrderBy());
        return rst;
    }

    /**
     * 登陆人属性
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/organizationType")
    @ResponseBody
    public ResultResponse organizationType() throws Exception {
        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(this.getCurUserProp().getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new ResultResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new ResultResponse(1, "账户没有绑定企业！");
        }
        FopAssociation fa = fopAssociationService.selectByDepartmentId(user.getDepartmentId());
        FopCompany fc = fopCompanyService.selectByDepartmentId(user.getDepartmentId());
        if (null != fa) {
            return new ResultResponse(ResultCode.SUCCESS, "团体账户", 1);
        } else if (null != fc) {
            return new ResultResponse(ResultCode.SUCCESS, "企业账号", 2);
        }
        return new ResultResponse(1, "账户没有绑定企业！");

    }


    /**
     * 完善团体信息
     *
     * @param fopAssMember
     * @param fopAssociation
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertAssociationInfo")
    @ResponseBody
    public MessageResponse insertAssociationInfo(FopAssMember fopAssMember, FopAssociation fopAssociation) throws Exception {
        if (CommonUtils.isBlank(fopAssMember.getAssId())) {
            return new MessageResponse(ResultCode.FAIL, "id不能为空");
        }
        fopAssociation.setId(fopAssMember.getAssId());
        fopAssociation.setPseronId(fopAssMember.getPname());
        MessageResponse result = fopAssociationService.updateFopAssociation(fopAssociation, this.getCurUserProp());
        if (ResultCode.FAIL == result.getStatus()) {
            throw new CustomException(result.getErrorMessage());
        }
         /*更新用户信息*/
        Users user = new Users();
        user.setUserId(this.getCurUserProp().getUserId());
        user.setName(fopAssociation.getFullName());
        MessageResponse rr = usersService.updateUsersById(user, this.getCurUserProp());
        if (ResultCode.FAIL == rr.getStatus()) {
            throw new CustomException(rr.getErrorMessage());
        }

        fopAssMemberService.deleteFopAssMemberByFopAssId(fopAssMember.getAssId(), this.getCurUserProp());
        MessageResponse result1 = fopAssMemberService.insertFopAssMember(fopAssMember, this.getCurUserProp());
        if (ResultCode.FAIL == result1.getStatus()) {
            throw new CustomException(result1.getErrorMessage());
        }
        return result1;
    }

    /**
     * 完善企业信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertCompanyInfo")
    @ResponseBody
    public ResultResponse insertAssociationInfo(String json) throws Exception {
        JSONObject jsonObj = JSON.parseObject(json);
        FopCompanyVo company = JSON.parseObject(jsonObj.getString("basicInfo"), FopCompanyVo.class);
        FopPersonVo person = JSON.parseObject(jsonObj.getString("legalPerson"), FopPersonVo.class);
        Type type = new TypeReference<List<FopCompanyOrg>>() {
        }.getType();
        List<FopCompanyOrg> orgs = JSON.parseObject(jsonObj.getString("org"), type);
        Type type1 = new TypeReference<List<FopCompanyContribution>>() {
        }.getType();
        List<FopCompanyContribution> contributes = JSON.parseObject(jsonObj.getString("contribute"), type1);

        /*更新企业*/
        company.setEmail(person.getEmail());
        MessageResponse rs1 = fopCompanyService.updateCompany(company, this.getCurUserProp());
        if (ResultCode.FAIL == rs1.getStatus()) {
            throw new CustomException(rs1.getErrorMessage());
        }
        String cid = company.getId();

        /*更新用户信息*/
        Users user = new Users();
        user.setEmail(person.getEmail());
        user.setUserId(this.getCurUserProp().getUserId());
        user.setName(company.getFullName());
        MessageResponse rr = usersService.updateUsersById(user, this.getCurUserProp());
        if (ResultCode.FAIL == rr.getStatus()) {
            throw new CustomException(rr.getErrorMessage());
        }
        /*更新法人信息*/
        person.setId(company.getPersonId());
        MessageResponse rs2 = fopPersonService.updateFopPerson(person, this.getCurUserProp());
        if (ResultCode.FAIL == rs2.getStatus()) {
            throw new CustomException(rs2.getErrorMessage());
        }
        /*删除贡献*/
        MessageResponse rs3 = fopCompanyContributionService.deleteFopCompanyContributionByCID(cid, this.getCurUserProp());
        if (ResultCode.FAIL == rs3.getStatus()) {
            throw new CustomException(rs3.getErrorMessage());
        }
        /*插入贡献*/
        for (FopCompanyContribution contribute : contributes) {
            contribute.setCompanyId(cid);
            MessageResponse rs4 = fopCompanyContributionService.insertFopCompanyContribution(contribute, this.getCurUserProp());
            if (ResultCode.FAIL == rs4.getStatus()) {
                throw new CustomException(rs4.getErrorMessage());
            }
        }


        /*删除团体*/
        MessageResponse rs5 = fopCompanyOrgService.deleteFopCompanyOrgByCID(cid, this.getCurUserProp());
        if (ResultCode.FAIL == rs5.getStatus()) {
            throw new CustomException(rs5.getErrorMessage());
        }
        /*插入团体*/
        for (FopCompanyOrg org : orgs) {
            org.setCompanyId(cid);
            MessageResponse rs6 = fopCompanyOrgService.insertFopCompanyOrg(org, this.getCurUserProp());
            if (ResultCode.FAIL == rs6.getStatus()) {
                throw new CustomException(rs6.getErrorMessage());
            }
        }
        return new ResultResponse(ResultCode.SUCCESS, "企业完善成功");
    }


    /**
     * @Description: TODO(获取诚信公示)
     * @param: @param id
     */
    @RequestMapping(value = "/selectIntegrityPublicityByPrimaryKey")
    @ResponseBody
    public ResultResponse selectIntegrityPublicityByPrimaryKey(String id) throws Exception {
        return this.integrityPublicityService.selectIntegrityPublicityByPrimaryKeyDo(id);
    }

    /**
     * @Description: TODO(获取团体详情)
     */
    @RequestMapping(value = "/selectAssociationInfo")
    @ResponseBody
    public ResultResponse selectFopAssociationByPrimaryKey() throws Exception {
        return this.fopAssociationService.selectAssociationByPrimaryKey(this.getCurUserProp());
    }


    /**
     * @Description: TODO(获取企业详情)
     */
    @RequestMapping(value = "/selectCompanyInfo")
    @ResponseBody
    public ResultResponse selectFopCompanyByPrimaryKey() throws Exception {
        return this.fopCompanyService.selectCompanyInfo(this.getCurUserProp());
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public ResultResponse getUserInfo() throws Exception {
        SingleResult<UsersVo> singleResult = usersService.selectUsersByPrimaryKey(this.getCurUserProp().getUserId());
        UsersVo user = singleResult.getValue();
        if (null == user) {
            return new ResultResponse(1, "没有注册");
        }
        if (CommonUtils.isBlank(user.getDepartmentId())) {
            return new ResultResponse(1, "账户没有绑定企业！");
        }
        FopAssociation fa = fopAssociationService.selectByDepartmentId(user.getDepartmentId());
        FopCompany fc = fopCompanyService.selectByDepartmentId(user.getDepartmentId());
        if (null != fa) {
            return new ResultResponse(ResultCode.SUCCESS, "个人中心信息", this.fopAssociationService.selectAssociationByPrimaryKey(this.getCurUserProp()));
        } else if (null != fc) {
            return new ResultResponse(ResultCode.SUCCESS, "个人中心信息", this.fopCompanyService.selectCompanyInfo(this.getCurUserProp()));
        }
        return new ResultResponse(1, "账户没有绑定企业！");
    }

    /**
     * 退会申请
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/applyQuit")
    @ResponseBody
    public ResultResponse applyQuit(String companyId) throws Exception {
        return fopFlowRecordService.applyQuit(companyId);
    }

}
