package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.partyschool.model.EvaluationIndex;
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
import com.huacainfo.ace.partyschool.model.Evaluating;
import com.huacainfo.ace.partyschool.service.EvaluatingService;
import com.huacainfo.ace.partyschool.vo.EvaluatingVo;
import com.huacainfo.ace.partyschool.vo.EvaluatingQVo;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/evaluating")
/**
 * @author: 王恩
 * @version: 2019-01-03
 * @Description: TODO(评测管理)
 */
public class EvaluatingController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluatingService evaluatingService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluatingVo>
     * @author: 王恩
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/findEvaluatingList")
    @ResponseBody
    public PageResult
            <EvaluatingVo> findEvaluatingList(EvaluatingQVo condition,
                                              PageParamNoChangeSord page) throws Exception {

        PageResult
                <EvaluatingVo> rst = this.evaluatingService
                .findEvaluatingList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluating
     * @Description: TODO(添加评测管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/insertEvaluating")
    @ResponseBody
    public MessageResponse insertEvaluating(String jsons) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        Evaluating evaluating = JSON.parseObject(jsonObj.getString("evaluating"), Evaluating.class);
        List<EvaluationIndex> list=new ArrayList<EvaluationIndex>(JSONArray.parseArray(jsonObj.getString("evaluationIndex"),EvaluationIndex.class));
        evaluating.setEvaluationIndexList(list);
        return this.evaluatingService.insertEvaluating(evaluating, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEvaluating
     * @Description: TODO(更新评测管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/updateEvaluating")
    @ResponseBody
    public MessageResponse updateEvaluating(String jsons) throws Exception {
        JSONObject jsonObj = JSON.parseObject(jsons);
        Evaluating evaluating = JSON.parseObject(jsonObj.getString("evaluating"), Evaluating.class);
        List<EvaluationIndex> list=new ArrayList<EvaluationIndex>(JSONArray.parseArray(jsonObj.getString("evaluationIndex"),EvaluationIndex.class));
        evaluating.setEvaluationIndexList(list);
        return this.evaluatingService.updateEvaluating(evaluating, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEvaluatingByPrimaryKey
     * @Description: TODO(获取评测管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Evaluating>
     * @author: 王恩
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/selectEvaluatingByPrimaryKey")
    @ResponseBody
    public SingleResult
            <EvaluatingVo> selectEvaluatingByPrimaryKey(String id) throws Exception {
        return this.evaluatingService.selectEvaluatingByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteEvaluatingByEvaluatingId
     * @Description: TODO(删除评测管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-03
     */
    @RequestMapping(value = "/deleteEvaluatingByEvaluatingId")
    @ResponseBody
    public MessageResponse deleteEvaluatingByEvaluatingId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evaluatingService.deleteEvaluatingByEvaluatingId(id, this.getCurUserProp());
    }

    @RequestMapping(value = "/softdel")
    @ResponseBody
    public MessageResponse softdel(String id) throws Exception {
        return this.evaluatingService.softdel(id, this.getCurUserProp());
    }
}
