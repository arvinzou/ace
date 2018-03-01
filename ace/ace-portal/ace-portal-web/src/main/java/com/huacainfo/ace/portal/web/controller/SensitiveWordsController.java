package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;

import com.huacainfo.ace.portal.model.SensitiveWords;

import com.huacainfo.ace.portal.service.SensitiveWordsService;
import com.huacainfo.ace.portal.vo.SensitiveWordsVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/sensitiveWords")
public class SensitiveWordsController extends PortalBaseController {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SensitiveWordsService sensitiveWordsService;


    @RequestMapping(value = "/findSensitiveWordsList.do")
    @ResponseBody
    public PageResult<SensitiveWordsVo> findSensitiveWordsList(SensitiveWords condition)
            throws Exception {
        PageResult<SensitiveWordsVo> rst = this.sensitiveWordsService.findSensitiveWordsList(condition);
        return rst;
    }


    @RequestMapping(value = "/deleteSensitiveWords.do")
    @ResponseBody
    public MessageResponse deleteSensitiveWords(String id) throws Exception {
        return this.sensitiveWordsService.deleteSensitiveWords(id, this.getCurUserProp());
    }



    @RequestMapping(value = "/insertSensitiveWords.do")
    @ResponseBody
    public MessageResponse insertSensitiveWordsRole(SensitiveWords condition)
            throws Exception {

        return this.sensitiveWordsService.insertSensitiveWords(condition, this.getCurUserProp());
    }
}
