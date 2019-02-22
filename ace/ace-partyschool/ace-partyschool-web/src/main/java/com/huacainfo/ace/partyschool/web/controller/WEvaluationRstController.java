package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.partyschool.model.EvaluationRstContent;
import com.huacainfo.ace.partyschool.service.ClassScheduleService;
import com.huacainfo.ace.partyschool.service.EvaluationRstContentService;
import com.huacainfo.ace.partyschool.service.EvaluationRstService;
import com.huacainfo.ace.partyschool.vo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

@Controller
@RequestMapping("/www/evaluationRst")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class WEvaluationRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstService evaluationRstService;

    @Autowired
    private EvaluationRstContentService evaluationRstContentService;

    @Autowired
    private ClassScheduleService classScheduleService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/findEvaluationRstList")
    @ResponseBody
    public ResultResponse findEvaluationRstList(EvaluationRstQVo condition, PageParamNoChangeSord page) throws Exception {
        condition.setUserId(this.getCurUserProp().getUserId());
        List<EvaluationRstVo> list = this.evaluationRstService.findEvaluationRstList(condition, page.getStart(), page.getLimit(), page.getOrderBy()).getRows();
        EvaluationRstContent evaluationRstContent=new EvaluationRstContent();
        evaluationRstContent.setUserId(this.getCurUserProp().getUserId());
        evaluationRstContent.setClassScheduleId(condition.getClassScheduleId());
        EvaluationRstContent rst = this.evaluationRstContentService.selectEvaluationRstContent(evaluationRstContent).getValue();
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("rst",rst);
        return new ResultResponse(ResultCode.SUCCESS,"成功",map);
    }


    @RequestMapping(value = "/findEvaluationRstListVo")
    @ResponseBody
    public PageResult<EvaluationRstVo> findEvaluationRstListVo(EvaluationRstQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<EvaluationRstVo> rst = this.evaluationRstService
                .findEvaluationRstListVo(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    @RequestMapping(value = "/insertEvaluationRstList")
    @ResponseBody
    public ResultResponse insertEvaluationRstList(String evaluationRst, String evaluationContent) throws Exception {
        List<EvaluationRst> list=new ArrayList<EvaluationRst>(JSONArray.parseArray(evaluationRst,EvaluationRst.class));
        EvaluationRstContent obj = JSON.parseObject(evaluationContent, EvaluationRstContent.class);
        return evaluationRstService.insertEvaluationRstList( list,  obj,this.getCurUserProp());
    }
}
