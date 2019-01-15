package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
import org.apache.velocity.runtime.directive.MacroParseException;
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
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.partyschool.service.EvaluationRstService;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstQVo;

import java.util.Map;

@Controller
@RequestMapping("/evaluationRst")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class EvaluationRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstService evaluationRstService;

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
    public PageResult<EvaluationRstVo> findEvaluationRstList(EvaluationRstQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<EvaluationRstVo> rst = this.evaluationRstService
                .findEvaluationRstList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
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


    /**
     * @throws
     * @Title:insertEvaluationRst
     * @Description: TODO(添加测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/insertEvaluationRst")
    @ResponseBody
    public MessageResponse insertEvaluationRst(String jsons) throws Exception {
        EvaluationRst obj = JSON.parseObject(jsons, EvaluationRst.class);
        return this.evaluationRstService.insertEvaluationRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEvaluationRst
     * @Description: TODO(更新测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/updateEvaluationRst")
    @ResponseBody
    public MessageResponse updateEvaluationRst(String jsons) throws Exception {
        EvaluationRst obj = JSON.parseObject(jsons, EvaluationRst.class);
        return this.evaluationRstService.updateEvaluationRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEvaluationRstByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRst>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/selectEvaluationRstByPrimaryKey")
    @ResponseBody
    public SingleResult<EvaluationRstVo> selectEvaluationRstByPrimaryKey(String id) throws Exception {
        return this.evaluationRstService.selectEvaluationRstByPrimaryKey(id);
    }


    @RequestMapping(value = "/statistics")
    @ResponseBody
    public ResultResponse statistics(String classScheduleId) throws Exception {
        return this.evaluationRstService.statistics(classScheduleId);
    }

    /**
     * @throws
     * @Title:deleteEvaluationRstByEvaluationRstId
     * @Description: TODO(删除测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/deleteEvaluationRstByEvaluationRstId")
    @ResponseBody
    public MessageResponse deleteEvaluationRstByEvaluationRstId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evaluationRstService.deleteEvaluationRstByEvaluationRstId(id, this.getCurUserProp());
    }

}
