package com.huacainfo.ace.woc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.model.Cases;
import com.huacainfo.ace.woc.service.CasesService;
import com.huacainfo.ace.woc.vo.CasesQVo;
import com.huacainfo.ace.woc.vo.CasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cases")
/**
 * @author: Arvin
 * @version: 2018-03-28
 * @Description: TODO(案件管理)
 */
public class CasesController extends WocBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CasesService casesService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(案件管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<CasesVo>
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/findCasesList")
    @ResponseBody
    public PageResult<CasesVo> findCasesList(CasesQVo condition,
                                             PageParamNoChangeSord page) throws Exception {
        PageResult<CasesVo> rst = this.casesService
                .findCasesList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertCases
     * @Description: TODO(添加案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/insertCases")
    @ResponseBody
    public MessageResponse insertCases(String jsons) throws Exception {
        Cases obj = JSON.parseObject(jsons, Cases.class);
        return this.casesService
                .insertCases(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateCases
     * @Description: TODO(更新案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/updateCases")
    @ResponseBody
    public MessageResponse updateCases(String jsons) throws Exception {
        Cases obj = JSON.parseObject(jsons, Cases.class);
        return this.casesService
                .updateCases(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectCasesByPrimaryKey
     * @Description: TODO(获取案件管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Cases>
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/selectCasesByPrimaryKey")
    @ResponseBody
    public SingleResult<CasesVo> selectCasesByPrimaryKey(String id)
            throws Exception {
        return this.casesService.selectCasesByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/deleteCasesByCasesId")
    @ResponseBody
    public MessageResponse deleteCasesByCasesId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.casesService.deleteCasesByCasesId(id,
                this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public MessageResponse auditCase(String ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new MessageResponse(1, "传入参数不能为空！");
        }

        return casesService.auditCase(ids, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/submitAudit")
    @ResponseBody
    public MessageResponse submitAuditCase(String ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new MessageResponse(1, "传入参数不能为空！");
        }

        return casesService.submitAuditCase(ids, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:deleteCasesByCasesId
     * @Description: TODO(删除案件管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-28
     */
    @RequestMapping(value = "/repeal")
    @ResponseBody
    public MessageResponse repealCase(String ids) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new MessageResponse(1, "传入参数不能为空！");
        }

        return casesService.repealCase(ids, this.getCurUserProp());
    }
}
