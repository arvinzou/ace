package com.huacainfo.ace.fop.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopQuestion;
import com.huacainfo.ace.fop.service.FopQuestionService;
import com.huacainfo.ace.fop.vo.FopQuestionQVo;
import com.huacainfo.ace.fop.vo.FopQuestionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fopQuestion")
/**
 * @author: Arvin
 * @version: 2018-05-07
 * @Description: TODO(法律帮助/政府诉求)
 */
public class FopQuestionController extends FopBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopQuestionService fopQuestionService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律帮助/政府诉求分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <FopQuestionVo>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @RequestMapping(value = "/findFopQuestionList")
    @ResponseBody
    public PageResult<FopQuestionVo> findFopQuestionList(FopQuestionQVo condition,
                                                         PageParamNoChangeSord page) throws Exception {
        PageResult
                <FopQuestionVo> rst = this.fopQuestionService
                .findFopQuestionList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertFopQuestion
     * @Description: TODO(添加法律帮助/政府诉求)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @RequestMapping(value = "/insertFopQuestion")
    @ResponseBody
    public MessageResponse insertFopQuestion(String jsons) throws Exception {
        FopQuestion obj = JSON.parseObject(jsons, FopQuestion.class);
        return this.fopQuestionService
                .insertFopQuestion(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateFopQuestion
     * @Description: TODO(更新法律帮助/政府诉求)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @RequestMapping(value = "/updateFopQuestion")
    @ResponseBody
    public MessageResponse updateFopQuestion(String jsons) throws Exception {
        FopQuestion obj = JSON.parseObject(jsons, FopQuestion.class);
        return this.fopQuestionService
                .updateFopQuestion(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectFopQuestionByPrimaryKey
     * @Description: TODO(获取法律帮助/政府诉求)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopQuestion>
     * @author: Arvin
     * @version: 2018-05-07
     */
    @RequestMapping(value = "/selectFopQuestionByPrimaryKey")
    @ResponseBody
    public SingleResult<FopQuestionVo> selectFopQuestionByPrimaryKey(String id) throws Exception {
        return this.fopQuestionService.selectFopQuestionByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteFopQuestionByFopQuestionId
     * @Description: TODO(删除法律帮助/政府诉求)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-07
     */
    @RequestMapping(value = "/deleteFopQuestionByFopQuestionId")
    @ResponseBody
    public MessageResponse deleteFopQuestionByFopQuestionId(String jsons)
            throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.fopQuestionService.deleteFopQuestionByFopQuestionId(id,
                this.getCurUserProp());
    }
}
