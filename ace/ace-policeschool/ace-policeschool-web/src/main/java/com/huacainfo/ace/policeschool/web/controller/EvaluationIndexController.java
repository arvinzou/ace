package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.EvaluationIndex;
import com.huacainfo.ace.policeschool.service.EvaluationIndexService;
import com.huacainfo.ace.policeschool.vo.EvaluationIndexQVo;
import com.huacainfo.ace.policeschool.vo.EvaluationIndexVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/evaluationIndex")
/**
 * @author: 王恩
 * @version: 2019-01-04
 * @Description: TODO(评测选项)
 */
public class EvaluationIndexController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationIndexService evaluationIndexService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(评测选项分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationIndexVo>
     * @author: 王恩
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/findEvaluationIndexList")
    @ResponseBody
    public PageResult
            <EvaluationIndexVo> findEvaluationIndexList(EvaluationIndexQVo condition,
                                                        PageParamNoChangeSord page) throws Exception {

        PageResult<EvaluationIndexVo> rst = this.evaluationIndexService
                .findEvaluationIndexList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluationIndex
     * @Description: TODO(添加评测选项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/insertEvaluationIndex")
    @ResponseBody
    public MessageResponse insertEvaluationIndex(String jsons) throws Exception {
        EvaluationIndex obj = JSON.parseObject(jsons, EvaluationIndex.class);
        return this.evaluationIndexService.insertEvaluationIndex(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEvaluationIndex
     * @Description: TODO(更新评测选项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/updateEvaluationIndex")
    @ResponseBody
    public MessageResponse updateEvaluationIndex(String jsons) throws Exception {
        EvaluationIndex obj = JSON.parseObject(jsons, EvaluationIndex.class);
        return this.evaluationIndexService.updateEvaluationIndex(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEvaluationIndexByPrimaryKey
     * @Description: TODO(获取评测选项)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationIndex>
     * @author: 王恩
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/selectEvaluationIndexByPrimaryKey")
    @ResponseBody
    public SingleResult
            <EvaluationIndexVo> selectEvaluationIndexByPrimaryKey(String id) throws Exception {
        return this.evaluationIndexService.selectEvaluationIndexByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteEvaluationIndexByEvaluationIndexId
     * @Description: TODO(删除评测选项)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-04
     */
    @RequestMapping(value = "/deleteEvaluationIndexByEvaluationIndexId")
    @ResponseBody
    public MessageResponse deleteEvaluationIndexByEvaluationIndexId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evaluationIndexService.deleteEvaluationIndexByEvaluationIndexId(id, this.getCurUserProp());
    }
}
