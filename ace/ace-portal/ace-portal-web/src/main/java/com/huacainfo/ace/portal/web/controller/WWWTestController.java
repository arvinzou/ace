package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.service.*;
import com.huacainfo.ace.portal.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/www/test")
public class WWWTestController extends PortalBaseController {
    private static final long serialVersionUID = 1L;

    @Autowired
    private EvaluatTplService evaluatTplService;

    @Autowired
    private EvaluatCaseService evaluatCaseService;
    @Autowired
    private EvaluatGaugeService evaluatGaugeService;
    @Autowired
    private EvaluatDataService evaluatDataService;

    @Autowired
    private EvaluatTypeService evaluatTypeService;

    /**
     * 获取测试模板列表
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEvaluatTpl.do")
    @ResponseBody
    public ResultResponse getEvaluatTplList(String id) throws Exception {
        return this.evaluatTplService.getEvaluatTplByPrimaryKey(id);
    }


    /**
     * 获取测试模板列表
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEvaluatTplList.do")
    @ResponseBody
    public ResultResponse getEvaluatTplList(EvaluatTplQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setSyid(this.getCurUserProp().getActiveSyId());
        return this.evaluatTplService.getEvaluatTplList(condition, page.getPage(), page.getLimit(), page.getOrderBy());
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getEvaluatCaseList.do")
    @ResponseBody
    public PageResult<EvaluatCaseVo> findEvaluatCaseList(EvaluatCaseQVo condition) throws Exception {
        PageResult<EvaluatCaseVo> rst = this.evaluatCaseService.findEvaluatCaseList(condition, 0, 200, null);
        return rst;
    }


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(题目分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<EvaluatCaseVo>
     * @author: 陈晓克
     * @version: 2018-06-09
     */
    @RequestMapping(value = "/getEvaluatCaseListVo.do")
    @ResponseBody
    public ResultResponse findEvaluatCaseListVo(EvaluatCaseQVo condition) throws Exception {
        return this.evaluatCaseService.findEvaluatCaseListVo(condition, 0, 200, null);
    }

    /**
     * 成绩评判
     *
     * @param condition
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEvaluation.do")
    @ResponseBody
    public ResultResponse getEvaluation(EvaluatGaugeQVo condition) throws Exception {
        Userinfo userinfo = getCurUserinfo();
        return this.evaluatGaugeService.getEvaluation(condition, userinfo);
    }


    /**
     * 成绩评判
     *
     * @param condition
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findEvaluatDataList.do")
    @ResponseBody
    public ResultResponse findEvaluatDataList(EvaluatDataQVo condition, PageParamNoChangeSord page) throws Exception {
        return this.evaluatDataService.findEvaluatDataLists(condition, page.getStart(), page.getLimit(), page.getOrderBy());
    }

    @RequestMapping(value = "/selectTestTypeList.do")
    @ResponseBody
    public Map<String, Object> selectAuthor() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("syid", this.getCurUserProp().getActiveSyId());
        return this.evaluatTypeService.selectType(params);
    }
}
