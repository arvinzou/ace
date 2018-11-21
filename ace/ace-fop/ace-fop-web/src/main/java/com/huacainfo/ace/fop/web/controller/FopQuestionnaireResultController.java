package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopQuestionnaireResult;
import com.huacainfo.ace.fop.service.FopQuestionnaireResultService;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultQVo;
import com.huacainfo.ace.fop.vo.FopQuestionnaireResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/questionnaireResult")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: (满意度调查)
 */
public class FopQuestionnaireResultController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionnaireResultService fopQuestionnaireResultService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: (满意度调查分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionnaireResultVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/findFopQuestionnaireResultList")
    @ResponseBody
    public PageResult<FopQuestionnaireResultVo> findFopQuestionnaireResultList(FopQuestionnaireResultQVo condition,
                                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<FopQuestionnaireResultVo> rst = this.fopQuestionnaireResultService
                .findFopQuestionnaireResultList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestionnaireResult
     * @Description: (添加满意度调查)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/insertFopQuestionnaireResult")
    @ResponseBody
    public MessageResponse insertFopQuestionnaireResult(String jsons) throws Exception {
        FopQuestionnaireResult obj = JSON.parseObject(jsons, FopQuestionnaireResult.class);
        return this.fopQuestionnaireResultService.insertFopQuestionnaireResult(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopQuestionnaireResult
     * @Description: (更新满意度调查)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/updateFopQuestionnaireResult")
    @ResponseBody
    public MessageResponse updateFopQuestionnaireResult(String jsons) throws Exception {
        FopQuestionnaireResult obj = JSON.parseObject(jsons, FopQuestionnaireResult.class);
        return this.fopQuestionnaireResultService.updateFopQuestionnaireResult(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopQuestionnaireResultByPrimaryKey
     * @Description: (获取满意度调查)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestionnaireResult>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/selectVoByPrimaryKey")
    @ResponseBody
    public SingleResult<FopQuestionnaireResultVo> selectVoByPrimaryKey(String id) throws Exception {
        return this.fopQuestionnaireResultService.selectVoByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopQuestionnaireResultByFopQuestionnaireResultId
     * @Description: (删除满意度调查)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/deleteByPrimaryKey")
    @ResponseBody
    public MessageResponse deleteByPrimaryKey(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopQuestionnaireResultService.deleteByPrimaryKey(id, this.getCurUserProp());
    }
}
