package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.fop.model.InformationService;
import com.huacainfo.ace.fop.service.InformationServiceService;
import com.huacainfo.ace.fop.vo.InformationServiceQVo;
import com.huacainfo.ace.fop.vo.InformationServiceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/informationService")
/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(信息服务)
 */
public class InformationServiceController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InformationServiceService corporateStyleService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(信息服务分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <InformationServiceVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/findInformationServiceList")
    @ResponseBody
    public PageResult<InformationServiceVo> findInformationServiceList(InformationServiceQVo condition,
                                                                       PageParamNoChangeSord page) throws Exception {
        PageResult<InformationServiceVo> rst = this.corporateStyleService
                .findInformationServiceList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertInformationService
     * @Description: TODO(添加信息服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/insertInformationService")
    @ResponseBody
    public MessageResponse insertInformationService(String jsons) throws Exception {
        InformationService obj = JSON.parseObject(jsons, InformationService.class);
        return this.corporateStyleService.insertInformationService(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateInformationService
     * @Description: TODO(更新信息服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/updateInformationService")
    @ResponseBody
    public MessageResponse updateInformationService(String jsons) throws Exception {
        InformationService obj = JSON.parseObject(jsons, InformationService.class);
        return this.corporateStyleService.updateInformationService(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectInformationServiceByPrimaryKey
     * @Description: TODO(获取信息服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<InformationService>
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/selectInformationServiceByPrimaryKey")
    @ResponseBody
    public SingleResult
            <InformationServiceVo> selectInformationServiceByPrimaryKey(String id) throws Exception {
        return this.corporateStyleService.selectInformationServiceByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteInformationServiceByInformationServiceId
     * @Description: TODO(删除信息服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    @RequestMapping(value = "/deleteInformationServiceByInformationServiceId")
    @ResponseBody
    public MessageResponse deleteInformationServiceByInformationServiceId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.corporateStyleService.deleteInformationServiceByInformationServiceId(id, this.getCurUserProp());
    }

    /**
     * 功能描述:  审核
     *
     * @param: id Fop_loan_Project.id
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/8 18:19
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse audit(String id, String auditResult, String auditOpinion) throws Exception {
        if (CommonUtils.isEmpty(id)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必备参数");
        }

        return corporateStyleService.audit(id, auditResult, auditOpinion, getCurUserProp());
    }
}
