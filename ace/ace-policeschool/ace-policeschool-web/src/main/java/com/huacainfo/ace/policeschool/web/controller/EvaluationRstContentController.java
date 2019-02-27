package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.EvaluationRstContent;
import com.huacainfo.ace.policeschool.service.EvaluationRstContentService;
import com.huacainfo.ace.policeschool.vo.EvaluationRstContentQVo;
import com.huacainfo.ace.policeschool.vo.EvaluationRstContentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/evaluationRstContent")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class EvaluationRstContentController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstContentService evaluationRstContentService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstContentVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/findEvaluationRstContentList")
    @ResponseBody
    public PageResult
            <EvaluationRstContentVo> findEvaluationRstContentList(EvaluationRstContentQVo condition,
                                                                  PageParamNoChangeSord page) throws Exception {

        PageResult
                <EvaluationRstContentVo> rst = this.evaluationRstContentService
                .findEvaluationRstContentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertEvaluationRstContent
     * @Description: TODO(添加测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/insertEvaluationRstContent")
    @ResponseBody
    public MessageResponse insertEvaluationRstContent(String jsons) throws Exception {
        EvaluationRstContent obj = JSON.parseObject(jsons, EvaluationRstContent.class);
        return this.evaluationRstContentService.insertEvaluationRstContent(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEvaluationRstContent
     * @Description: TODO(更新测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/updateEvaluationRstContent")
    @ResponseBody
    public MessageResponse updateEvaluationRstContent(String jsons) throws Exception {
        EvaluationRstContent obj = JSON.parseObject(jsons, EvaluationRstContent.class);
        return this.evaluationRstContentService.updateEvaluationRstContent(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEvaluationRstContentByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRstContent>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/selectEvaluationRstContentByPrimaryKey")
    @ResponseBody
    public SingleResult
            <EvaluationRstContentVo> selectEvaluationRstContentByPrimaryKey(String id) throws Exception {
        return this.evaluationRstContentService.selectEvaluationRstContentByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteEvaluationRstContentByEvaluationRstContentId
     * @Description: TODO(删除测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/deleteEvaluationRstContentByEvaluationRstContentId")
    @ResponseBody
    public MessageResponse deleteEvaluationRstContentByEvaluationRstContentId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evaluationRstContentService.deleteEvaluationRstContentByEvaluationRstContentId(id, this.getCurUserProp());
    }

}
