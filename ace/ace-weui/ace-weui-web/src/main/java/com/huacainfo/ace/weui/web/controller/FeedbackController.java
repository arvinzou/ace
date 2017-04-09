package com.huacainfo.ace.weui.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.weui.model.Feedback;
import com.huacainfo.ace.weui.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/www/feedback")
public class FeedbackController extends WeuiBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedbackService feedbackService;



    @RequestMapping(value = "/insertFeedback.do")
    @ResponseBody
    public MessageResponse insertFeedback(String title,String docText,String j_captcha_weui) throws Exception {

        String s_j_captcha_weui=(String) this.getRequest().getSession().getAttribute("j_captcha_weui");
        if(!j_captcha_weui.equals(s_j_captcha_weui)){
            return new MessageResponse(1,"验证码错误！");
        }
        Feedback obj = new Feedback();
        obj.setTitle(title);
        obj.setDocText(docText);
        return this.feedbackService.insertFeedback(obj);
    }
}
