package com.huacainfo.ace.pwl.web.controller;

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
import com.huacainfo.ace.pwl.model.Feedback;
import com.huacainfo.ace.pwl.service.FeedbackService;
import com.huacainfo.ace.pwl.vo.FeedbackVo;
import com.huacainfo.ace.pwl.vo.FeedbackQVo;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends PwlBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/findFeedbackList.do")
    @ResponseBody
    public PageResult<FeedbackVo> findFeedbackList(FeedbackQVo condition, PageParamNoChangeSord page) throws Exception {
        PageResult<FeedbackVo> rst = this.feedbackService.findFeedbackList(condition, page.getStart(), page.getLimit(),
                page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/insertFeedback.do")
    @ResponseBody
    public MessageResponse insertFeedback(String jsons) throws Exception {
        Feedback obj = JSON.parseObject(jsons, Feedback.class);
        return this.feedbackService.insertFeedback(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/updateFeedback.do")
    @ResponseBody
    public MessageResponse updateFeedback(String jsons) throws Exception {
        Feedback obj = JSON.parseObject(jsons, Feedback.class);
        return this.feedbackService.updateFeedback(obj, this.getCurUserProp());
    }

    @RequestMapping(value = "/selectFeedbackByPrimaryKey.do")
    @ResponseBody
    public SingleResult<Feedback> selectFeedbackByPrimaryKey(String id) throws Exception {
        return this.feedbackService.selectFeedbackByPrimaryKey(id);
    }

    @RequestMapping(value = "/deleteFeedbackByFeedbackId.do")
    @ResponseBody
    public MessageResponse deleteFeedbackByFeedbackId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.feedbackService.deleteFeedbackByFeedbackId(id, this.getCurUserProp());
    }
}
