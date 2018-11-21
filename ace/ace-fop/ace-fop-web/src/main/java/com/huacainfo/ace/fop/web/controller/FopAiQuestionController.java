package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopAiQuestion;
import com.huacainfo.ace.fop.service.FopAiQuestionService;
import com.huacainfo.ace.fop.vo.FopAiQuestionQVo;
import com.huacainfo.ace.fop.vo.FopAiQuestionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopAiQuestion")
/**
 * @author: Arvin
 * @version: 2018-05-11
 * @Description: TODO(诉求服务)
 */
public class FopAiQuestionController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopAiQuestionService fopAiQuestionService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求服务分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopAiQuestionVo>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/findFopAiQuestionList")
    @ResponseBody
    public PageResult<FopAiQuestionVo> findFopAiQuestionList(FopAiQuestionQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FopAiQuestionVo> rst = this.fopAiQuestionService
                .findFopAiQuestionList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopAiQuestion
     * @Description: TODO(添加诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/insertFopAiQuestion")
    @ResponseBody
    public MessageResponse insertFopAiQuestion(String jsons) throws Exception {
        FopAiQuestion obj = JSON.parseObject(jsons, FopAiQuestion.class);
        return this.fopAiQuestionService.insertFopAiQuestion(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopAiQuestion
     * @Description: TODO(更新诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/updateFopAiQuestion")
    @ResponseBody
    public MessageResponse updateFopAiQuestion(String jsons) throws Exception {
        FopAiQuestion obj = JSON.parseObject(jsons, FopAiQuestion.class);
        return this.fopAiQuestionService.updateFopAiQuestion(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopAiQuestionByPrimaryKey
     * @Description: TODO(获取诉求服务)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopAiQuestion>
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/selectFopAiQuestionByPrimaryKey")
    @ResponseBody
    public SingleResult
            <FopAiQuestionVo> selectFopAiQuestionByPrimaryKey(String id) throws Exception {
        return this.fopAiQuestionService.selectFopAiQuestionByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopAiQuestionByFopAiQuestionId
     * @Description: TODO(删除诉求服务)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-11
     */
    @RequestMapping(value = "/deleteFopAiQuestionByFopAiQuestionId")
    @ResponseBody
    public MessageResponse deleteFopAiQuestionByFopAiQuestionId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopAiQuestionService.deleteFopAiQuestionByFopAiQuestionId(id, this.getCurUserProp());
    }
}
